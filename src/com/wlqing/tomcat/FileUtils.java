package com.wlqing.tomcat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * �ļ������ࣺר�Ŷ�ȡ��̬��Դ
 */
public class FileUtils {
	/*
	 * 
	 */
	public static String getFileContent(String path) {
		StringBuffer sb = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;
		// �ַ���
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
