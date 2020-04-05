package service;

import bean.ClientInfos;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.Response;
import response.TextResponse;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * netty Handle，即业务逻辑处理
 * @author wangxi
 * @date 2020-04-05 15:56
 */
@ChannelHandler.Sharable
public class ChattingServiceHandle extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChattingServiceHandle.class);


    /**
     * 保持上线的channel
     */
    Map<String, Channel> onlineUserMap = new HashMap<String, Channel>();
    ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 接收客户端发送数据
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        ByteBuf msg = (ByteBuf) o;

        ClientInfos clientInfos = null;
        try {

            clientInfos = new Gson().fromJson(msg.toString(Charset.defaultCharset()), ClientInfos.class);
        }catch (Throwable ex){
            logger.error("格式化客户端数据错误：{}", ex.getMessage());
        }

        if(clientInfos == null){
            logger.error("无法获取客户端发送数据");
            return;
        }

        logger.info(msg.toString(Charset.defaultCharset()));


        String receiver = clientInfos.getReceiver();
        String sender = clientInfos.getSender();
        String uuid = clientInfos.getSequence();
        //文本内容
        String content = clientInfos.getContent();


        //解析协议
        switch (clientInfos.getAction()){

            //认证消息
            case "auth":

                //保存在线
                onlineUserMap.put(clientInfos.getSender(),channelHandlerContext.channel());
                break;
            //发送群消息
            case "all":

                onlineUserMap.values().stream().forEach(it->{
                    it.eventLoop().execute(()->{
                        Response authResponse = new TextResponse(uuid, sender, receiver, content);
                        it.writeAndFlush(Unpooled.copiedBuffer(authResponse.getData(), Charset.defaultCharset()));
                    });
                });
                break;

            //发送单消息
            case "text":
                //获取消息接收着
                Channel receiverChannel = onlineUserMap.get(receiver);

                if (receiverChannel != null) {
                    //接收者上线，发送消息
                    receiverChannel.eventLoop().execute(()->{
                        Response authResponse = new TextResponse(uuid, sender, receiver, content);
                        receiverChannel.writeAndFlush(Unpooled.copiedBuffer(authResponse.getData(), Charset.defaultCharset()));
                        //receiverChannel.writeAndFlush(authResponse.getData());

                    });
                }
                break;
        }

    }
}
