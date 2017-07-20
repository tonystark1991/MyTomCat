package com.wlqing.tomcat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * 文件工具类：专门读取静态资源
 */
public class FileUtils {
	/*
	 * 
	 */
	public static String getFileContent(String path) {
		StringBuffer sb = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;
		// 字符流
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
