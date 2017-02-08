package com.ixiaofan.tools;
 
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ixiaofan.entity.Account;
/**
 * 发送邮件 
 * @author ixiaofan
 * @version 创建时间：2016年9月13日 上午9:18:06
 */
public class SendEmail {
	
	/**
	 * 发送验证信息
	 * @param account 账号对象
	 */
	public static void MailValidate(Account account) {
		String email = account.getEmail();
		String username = account.getUsername();
		String validatecode = account.getValidatecode();
		StringBuffer sb=new StringBuffer("您的账号"); 
		sb.append(username);
		sb.append("您注册了***网站，该激活邮件24小时内生效，只能使用一次，请尽快激活，否则将重新注册账号！<br>");
		sb.append("请点击下面链接激活账号");
        sb.append("<a href='http://localhost/account/activation?username=");   
        sb.append(username);  
        sb.append("&validateCode=");  
        sb.append(validatecode);  
        sb.append("'><font style='color:red'>点击激活</font></a>"); 
		SendEmail.sendEmail(email,"账号激活", sb.toString());
	}
	
	/**
	 * 发送邮件
	 * @param email 收件人邮箱地址
	 * @param subject 发送邮件标题
	 * @param content 发送邮件内容
	 */
    public static void sendEmail(String email,String subject,String content) {
        //建立邮件会话
          Properties pro = new Properties();
          pro.put("mail.smtp.host","smtp.163.com");//存储发送邮件的服务器
          pro.put("mail.smtp.auth","true");  //通过服务器验证
           
          Session s =Session.getInstance(pro); //根据属性新建一个邮件会话
          //s.setDebug(true);
           
          //由邮件会话新建一个消息对象
          MimeMessage message = new MimeMessage(s);
           
          //设置邮件
          InternetAddress fromAddr = null;
          InternetAddress toAddr = null;
           
          try
          {
           fromAddr = new InternetAddress("happyfanminjie@163.com");   //邮件发送地址
           message.setFrom(fromAddr);         //设置发送地址
            
           toAddr = new InternetAddress(email);       //邮件接收地址
           message.setRecipient(Message.RecipientType.TO, toAddr);  //设置接收地址
            
           message.setSubject(subject);   //设置邮件标题
        
           message.setContent(content,"text/html;charset=UTF-8");
                    
           message.setSentDate(new Date()); //设置邮件日期
            
           message.saveChanges();    //保存邮件更改信息
 
           Transport transport = s.getTransport("smtp");
           transport.connect("smtp.163.com", "happyfanminjie@163.com", "fmj252816399"); //服务器地址，邮箱账号，邮箱密码
           transport.sendMessage(message, message.getAllRecipients());   //发送邮件
           transport.close();//关闭
           System.out.println("邮件发送成功！");
          }
          catch (Exception e)
          {
           System.out.println("邮件发送失败！");
           e.printStackTrace();
          }
          
         }
    }
