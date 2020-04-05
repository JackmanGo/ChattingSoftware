import bean.ClientInfos;
import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @author wangxi
 * @date 2020-04-05 17:05
 */
public class NettyMsg {

    //发送auth
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler() {


                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {

                        ClientInfos clientInfos = new ClientInfos();
                        clientInfos.setSender("用户3");
                        clientInfos.setReceiver("用户1");
                        clientInfos.setAction("text");
                        clientInfos.setContent("在吗？我是用户3");
                        ctx.writeAndFlush(Unpooled.copiedBuffer(new Gson().toJson(clientInfos), CharsetUtil.UTF_8));

                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

                        ByteBuf byteBuf = (ByteBuf) o;
                        System.out.println(byteBuf.toString(Charset.defaultCharset()));
                    }
                });

        bootstrap.connect("127.0.0.1",9995).sync();
    }
}
