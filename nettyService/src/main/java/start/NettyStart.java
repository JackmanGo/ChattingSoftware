package start;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import service.ChattingServiceHandle;

import java.net.InetSocketAddress;

/**
 * netty初始化启动
 * @author wangxi
 * @date 2020-04-05 15:58
 */
public class NettyStart {

    /**
     * 启动netty服务器，等待链接
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {

        //事件循环器bossGroup只处理连接请求
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        //事件循环器workGroup客户端业务处理
        EventLoopGroup workEventLoopGroup = new NioEventLoopGroup();

        //两个EventLoopGroup来处理所有通道的 IO 事件
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
                //设置通道类型：oio阻塞模式 or 非阻塞模式nio
                .channel(NioServerSocketChannel.class)
                //设置线程队列得到链接的个数
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new LoggingHandler(LogLevel.INFO))
                //设置保持活动链接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //设置本地地址，也可以通过 bind(...)或 connect(...)
                .localAddress(new InetSocketAddress(9995))
                //通过 ChannelInitializer 初始化 handlers
                .childHandler(new ChattingServiceHandle());

        serverBootstrap.bind().sync();
    }

    public static void main(String[] args) throws InterruptedException {

        new NettyStart().start();
    }
}
