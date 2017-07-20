package com.wlqing.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	// 计数器
	private static int count = 0;

	public static void main(String[] args) {
		// 提升作用域
		ServerSocket ss = null;
		Socket socket = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = format.format(new Date());

		try {
			ss = new ServerSocket(8888);
			System.out.println("服务器已经初始化了，等待客户端连接中.........");
			while (true) {
				socket = ss.accept();
				count++;
				System.out.println("第" + count + "次连接服务器");
				// 拿到输入流，有相应的请求信息
				InputStream is = socket.getInputStream();
				Request request = new Request(is);
				// 发送服务返回信息
				OutputStream os = socket.getOutputStream();
				Response response = new Response(os);
				
				//业务逻辑
				String uri= request.getUri();
				//静态资源的请求
				if(isStatic(uri)){
					//读取的字节是万能格式
					response.writeHtmlFile(uri.substring(1));
				}else if(uri.endsWith(".action")){
					if(uri.endsWith("/login.action")){
						//取账户密码
						loginServlet servlet=new loginServlet();
						servlet.service(request, response);
					}
				}
				//这里要关闭
				socket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	//判断这个是不是请求静态资源(.html .css .jpg .js.....)
	public static boolean isStatic(String uri){
		String [] suffixs={"html","css","js","jpg","jpeg","png"};
		boolean isStatic =false;
		for(String suffix: suffixs){
			if(uri.endsWith("."+suffix)){
				isStatic=true;
				break;
			}
		}
		return isStatic;
	}

}
