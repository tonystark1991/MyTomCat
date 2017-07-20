package com.wlqing.tomcat;

import java.io.IOException;
import java.io.InputStream;
/*
 * ���������ࣺ����uri ������Դ��ַ
 */
import java.util.HashMap;

public class Request {
	// ��Ҫ��ʲô��Դ����Ҫ֪��uri ȷ����Դ
	private String uri;
	// ����һ�������ַ�
	private String pString;
	// HandlerMap
	private HashMap<String, String> paramMap = new HashMap<>();
	
	//ȡ�ÿͻ��ύ����
	public String getParamName(String key){
		return paramMap.get(key);
	}
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Request(InputStream is) throws IOException {
		byte[] buff = new byte[1024];
		// ������ÿ�ζ���������Ϣ�ŵ�����buff��
		int len = is.read(buff);
		if (len > 0) {
			String msg = new String(buff, 0, len);
			// ȡ��uri��·��
			int start = msg.indexOf("GET") + 4;
			if (msg.indexOf("POST") != -1) {
				start = msg.indexOf("POST") + 5;
			}
			int end = msg.indexOf("HTTP/1.1") - 1;
			uri = msg.substring(start, end);
			if (msg.startsWith("POST")) {
				int paramStart = msg.lastIndexOf("\n");
				pString = msg.substring(paramStart + 1);
				// userName=admin&password=123
				System.out.println("----" + pString + "---");
				String[] parms = pString.split("&");
				for (String parm : parms) {
					String[] temp = parm.split("=");
					paramMap.put(temp[0], temp[1]);
				}
			}

			uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1") - 1);
			System.out.println("----" + uri + "----");
		} else {
			System.out.println("bad request!");
		}
	}
}
