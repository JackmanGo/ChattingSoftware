package javaSocketService;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import com.google.gson.Gson;


import javaBean.ClientInfos;
import response.AskForResponse;
import response.AuthResponse;
import response.Response;
import response.TextResponse;

import javax.annotation.Resource;


public class LogicThread extends Thread {

	static Object lock = new Object();
	static volatile  boolean flag = true;

	Socket socket;
	InputStream inputStream;
	OutputStream outputStream;
	Map<String,Socket> all_socket;
	public LogicThread(Socket socket,Map<String,Socket> all_socket) {
		this.socket = socket;
		this.all_socket = all_socket;
		start(); // 启动线程
	}
	public void run() {
				byte[] buf = new byte[1024];
				try {
					// 初始化流
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
					// 读取数据
			/*	int len = inputStream.read(buf);
				System.out.println(new String(buf,0,len));
				// 反馈数据
				outputStream.write("收到".getBytes());
				*/
					int len = 0;
					System.out.println("进入阻塞 ........准备read");
					while ((len = (inputStream.read(buf))) > 0) {
						String json = new String(buf, 0, len);
						System.out.println(json);
						Gson gson = new Gson();
						ClientInfos clientInfos = gson.fromJson(json, ClientInfos.class);
						String action = clientInfos.getAction();
						String username = clientInfos.getSender();
						String uuid = clientInfos.getSequence();
						String type=clientInfos.getType();
						all_socket.put(username,socket);
						if (action != null) {
							//认证的消息
							if ("auth".equals(action)) {
								//用户的认证
								all_socket.put(username, socket);
								if("request".equals(type)){
									//用户验证成功
									Response authResponse = new AuthResponse(uuid);
									outputStream.write(authResponse.getData().getBytes());
								}
							}//发送的消息
							else if ("text".equals(action)) {
								String receiver = clientInfos.getReceiver();
								String sender = clientInfos.getSender();
								Socket receiver_socket = all_socket.get(receiver);
								String content = clientInfos.getContent();
								if (receiver_socket != null) {
									//接收者上线，发送消息
									Response authResponse = new TextResponse(uuid, sender, receiver, content);
									receiver_socket.getOutputStream().write(authResponse.getData().getBytes());
								}
								//请求添加好友的请求
							}else if("ask_for".equals(action)){
                                String receiver = clientInfos.getReceiver();
                                String sender = clientInfos.getSender();
                                Socket receiver_socket = all_socket.get(receiver);
                                String content = clientInfos.getContent();
                                if(receiver_socket!=null){
									Response ask_forResponse = new AskForResponse(sender,receiver,content);
									System.out.println("准备发送数据");
									System.out.println(ask_forResponse.getData());
									System.out.println("完成发送数据");
									receiver_socket.getOutputStream().write(ask_forResponse.getData().getBytes());
                                }else {
									System.out.println("用户未上线");
								}
							}
						}
						// 反馈数据
			/*	outputStream.write("收到".getBytes());*/
						System.out.println("阻塞结束 .........read结束.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
			close();
		}
	/**
	 * 
	 * 关闭流和连接
	 * 
	 */
	private void close() {
		try {
			// 关闭流和连接
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (Exception e) {
		}
	}
}