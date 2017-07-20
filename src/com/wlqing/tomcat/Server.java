package com.wlqing.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	// ������
	private static int count = 0;

	public static void main(String[] args) {
		// ����������
		ServerSocket ss = null;
		Socket socket = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = format.format(new Date());

		try {
			ss = new ServerSocket(8888);
			System.out.println("�������Ѿ���ʼ���ˣ��ȴ��ͻ���������.........");
			while (true) {
				socket = ss.accept();
				count++;
				System.out.println("��" + count + "�����ӷ�����");
				// �õ�������������Ӧ��������Ϣ
				InputStream is = socket.getInputStream();
				Request request = new Request(is);
				// ���ͷ��񷵻���Ϣ
				OutputStream os = socket.getOutputStream();
				Response response = new Response(os);
				
				//ҵ���߼�
				String uri= request.getUri();
				//��̬��Դ������
				if(isStatic(uri)){
					//��ȡ���ֽ������ܸ�ʽ
					response.writeHtmlFile(uri.substring(1));
				}else if(uri.endsWith(".action")){
					if(uri.endsWith("/login.action")){
						//ȡ�˻�����
						loginServlet servlet=new loginServlet();
						servlet.service(request, response);
					}
				}
				//����Ҫ�ر�
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
	//�ж�����ǲ�������̬��Դ(.html .css .jpg .js.....)
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
