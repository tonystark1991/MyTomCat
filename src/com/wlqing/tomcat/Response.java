package com.wlqing.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/*
 * ��Ӧ�ķ�װ�ࣺ����Щ��Ϣ���ͻ���
 */
public class Response {
	private OutputStream os = null;

	public Response(OutputStream os) {
		this.os = os;
	}

	/*
	 * ��Ӧ��̬�������
	 */
	public void writeHtmlFile(String path) throws IOException {
		String htmlConteString = FileUtils.getFileContent(path);
		os.write(htmlConteString.getBytes());
		os.flush();
		os.close();
	}

	/*
	 * ��Ӧ��̬�������
	 */
	public void writeConten(String content) throws IOException {
		os.write(content.getBytes());
		os.flush();
		os.close();
	}
}
