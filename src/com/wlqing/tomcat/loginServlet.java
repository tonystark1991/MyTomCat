package com.wlqing.tomcat;

import java.io.IOException;

public class loginServlet {
	public void service(Request request,Response response) throws IOException{
		String userName=request.getParamName("userName");
		String password=request.getParamName("pwd");
		if(userName!=null&&userName.equals("admin")&&password!=null&&password.equals("123")){
			response.writeHtmlFile("htmlfile/welcome.html");
		}else{
			response.writeHtmlFile("htmlfile/error.html");
		}
	}
}
