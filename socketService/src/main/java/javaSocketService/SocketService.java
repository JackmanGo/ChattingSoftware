package javaSocketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketService {
    static Map<String, Socket> all_socket = new HashMap<String, Socket>();
    private static final Logger logger = LoggerFactory.getLogger(SocketService.class);
    public static void startTCPService() {
        //定义一个集合来保持所以上线的客户端
        Socket socket = null;
        ServerSocket serviceSocket = null;
        try {
            //服务器端接收消息的类。定制端口号为8888
            serviceSocket = new ServerSocket(10000);
            logger.info("服务器已经启动");
            while (true) {
                //获取socket。这个方法是阻塞式的
                logger.info("进入阻塞......");
                socket = serviceSocket.accept();
                logger.info("阻塞结束......");
                //一但获取连接后就开子线程来处理
                new LogicThread(socket, all_socket);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                serviceSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /*  
	public static void main(String[] args) {

          ServerSocket serverSocket = null;

          Socket socket = null;

          OutputStream os = null;

          InputStream is = null;

          //监听端口号

          int port = 10000;

          try {

                   //建立连接

                   serverSocket = new ServerSocket(port);

                   //获得连接

                   socket = serverSocket.accept();

                   //接收客户端发送内容

                   is = socket.getInputStream();

                   byte[] b = new byte[1024];

                   int n = is.read(b);

                   //输出

                   logger.info("客户端发送内容为：" + new String(b,0,n));

                   //向客户端发送反馈内容

                   os = socket.getOutputStream();

                   os.write(b, 0, n);

          } catch (Exception e) {

                   e.printStackTrace();

          }finally{

                   try{

                            //关闭流和连接

                            os.close();

                            is.close();

                            socket.close();

                            serverSocket.close();

                   }catch(Exception e){}

          }

}
*/
}
