package com.wlqing.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/*
 * 响应的封装类：就是些信息给客户端
 */
public class Response {
	private OutputStream os = null;

	public Response(OutputStream os) {
		this.os = os;
	}

	/*
	 * 响应静态输出请求
	 */
	public void writeHtmlFile(String path) throws IOException {
		String htmlConteString = FileUtils.getFileContent(path);
		os.write(htmlConteString.getBytes());
		os.flush();
		os.close();
	}

	/*
	 * 响应动态输出请求
	 */
	public void writeConten(String content) throws IOException {
		os.write(content.getBytes());
		os.flush();
		os.close();
	}
}
