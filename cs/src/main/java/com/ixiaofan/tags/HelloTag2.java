package com.ixiaofan.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag2 extends SimpleTagSupport {
	
	private String message;
	
	public void setMessage(String message){
		this.message=message;
	}
	
	StringWriter sw = new StringWriter();
	
	@Override
	public void doTag() throws JspException, IOException {
		if(message!=null){
			/* 从属性中使用消息 */
			JspWriter jw = getJspContext().getOut();
			jw.println("<h1 style='color:red'>"+message+"</h1>");
		}else{
			/* 从内容体中使用消息 */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
