package com.teardowall.mail;

import java.io.IOException;

import javax.mail.MessagingException;

public class MailHelper {
	   
	  /**
	   * 普通方式发送邮件内容
	   * 
	   * @param receiver 收件人地址
	   * @param subject 邮件主题
	   * @param maiBody 邮件正文
	   */
	  public static void sendMail(String receiver, String subject, String maiBody) throws IOException, MessagingException {
	    String sender = MailConfig.getSender();
	    MailSender mail = new MailSender();
	    mail.setSubject(subject);
	    mail.setBody(maiBody);
	    mail.setReceiver(receiver);
	    mail.setSender(sender);
	    mail.sendout();
	  }
}
