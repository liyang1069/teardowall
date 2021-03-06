package com.teardowall.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
	  private MimeMessage mimeMsg; // MIME邮件对象
	  private Session session; // 邮件会话对象
	  private Properties props; // 系统属性
	  private Multipart multipart; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成
	  private String server;
	  private String username;// 发件人的用户名
	  private String password;// 发件人的密码
	  private String nickname;// 发件人的昵称
	  private static Logger log = LoggerFactory.getLogger(MailSender.class);

	  /**
	   * 有参构造器
	   * 
	   * @param smtp
	   */
	  public MailSender(String smtp, String username, String password, String nickname) {
	    this.username = username;
	    this.password = password;
	    this.nickname = nickname;
	    this.server = smtp;
	    setSmtpHost();
	    createMimeMessage();
	  }
	  
	  public MailSender(){
		this.username = MailConfig.getUsername();
	    this.password = MailConfig.getPassword();
	    this.nickname = MailConfig.getNickname();
	    this.server = MailConfig.getServer();
	    setSmtpHost();
	    createMimeMessage();
	  }

	  /**
	   * 设置邮件发送的SMTP主机
	   * 
	   * @Date:2014年4月26日 上午10:20:34
	   * @author
	   * @param hostName SMTP 发送主机
	   * @Description:
	   * @return void
	   */
	  public void setSmtpHost() {
	    if (props == null) {
	      props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      //props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	      props.put("mail.host", server);
	    }

	  }

	  /**
	   * 创建邮件对象
	   * 
	   * @Date:2014年4月26日 上午10:26:34
	   * @author
	   * @return
	   * @Description:
	   * @return boolean
	   */
	  public void createMimeMessage() {
	    session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(username, password);
	      }
	    });
	    // 创建MIME邮件对象
	    mimeMsg = new MimeMessage(session);
	    multipart = new MimeMultipart();
	    log.debug(" create session and mimeMessage success");
	  }


	  /**
	   * 设置发送邮件的主题
	   * 
	   * @Date:2014年4月26日 上午10:26:34
	   * @author
	   * @param subject 邮件的主题
	   * @throws UnsupportedEncodingException
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void setSubject(String subject) throws UnsupportedEncodingException, MessagingException {
	    mimeMsg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
	    log.debug("set mail subject success, subject= " + subject);

	  }

	  /**
	   * 
	   * @Date:2014年4月26日 上午10:28:34
	   * @author
	   * @param mailBody 邮件的正文内容
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void setBody(String mailBody) throws MessagingException {
	    BodyPart bp = new MimeBodyPart();
	    bp.setContent("" + mailBody, "text/html;charset=utf-8");
	    multipart.addBodyPart(bp);
	    log.debug("set mail body content success,mailBody= " + mailBody);
	  }

	  /**
	   * 添加邮件附件
	   * 
	   * @Date:2014年4月26日 上午10:30:40
	   * @author
	   * @param filePath 文件绝对路径
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   * @throws UnsupportedEncodingException
	   */
	  public void addFileAffix(String filePath) throws MessagingException, UnsupportedEncodingException {
	    File file = new File(filePath);
	    if (file.exists()) {
	      BodyPart bp = new MimeBodyPart();
	      FileDataSource fileds = new FileDataSource(filePath);
	      bp.setDataHandler(new DataHandler(fileds));
	      bp.setFileName(MimeUtility.encodeText(fileds.getName()));
	      multipart.addBodyPart(bp);
	      log.debug("mail add file success,filename= " + filePath);
	    } else {
	      log.debug("mail add file fail,could not find file: " + filePath);
	    }

	  }

	  /**
	   * 设置发件人邮箱地址
	   * 
	   * @Date:2014年4月26日 上午10:35:54
	   * @author
	   * @param sender 发件人邮箱地址
	   * @throws UnsupportedEncodingException
	   * @throws AddressException
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void setSender(String sender) throws UnsupportedEncodingException, AddressException, MessagingException {
	    nickname = MimeUtility.encodeText(nickname, "utf-8", "B");
	    mimeMsg.setFrom(new InternetAddress(nickname + " <" + sender + ">"));
	    log.debug(" set mail sender and nickname success , sender= " + sender + ",nickname=" + nickname);
	  }

	  /**
	   * 设置收件人邮箱地址
	   * 
	   * @Date:2014年4月26日 上午10:41:06
	   * @author
	   * @param receiver 收件人邮箱地址
	   * @throws AddressException
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void setReceiver(String receiver) throws AddressException, MessagingException {
	    mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
	    log.debug("set mail receiver success,receiver = " + receiver);
	  }

	  public void setReceivers(List<String> receivers) throws AddressException, MessagingException {
	    InternetAddress[] sendTo = new InternetAddress[receivers.size()];
	    for (int i = 0; i < receivers.size(); i++) {
	      if (StringUtils.isNotBlank(receivers.get(i))) {
	        sendTo[i] = new InternetAddress(receivers.get(i));
	      }
	    }
	    mimeMsg.setRecipients(Message.RecipientType.TO, sendTo);
	    log.debug("set mail receiver success,receivers = " + receivers.toString());
	  }

	  /**
	   * 设置抄送人的邮箱地址
	   * 
	   * @Date:2014年4月26日 上午10:42:14
	   * @author
	   * @param copyto 抄送人邮箱地址
	   * @throws AddressException
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void setCopyTo(String copyto) throws AddressException, MessagingException {
	    mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyto));
	    log.debug("set mail copyto receiver success,copyto = " + copyto);
	  }

	  /**
	   * 设置发件人用户名密码进行发送邮件操作
	   * 
	   * @Date:2014年4月26日 上午10:44:01
	   * @author
	   * @throws MessagingException
	   * @Description:
	   * @return void
	   */
	  public void sendout() throws MessagingException {
	    mimeMsg.setContent(multipart);
	    mimeMsg.saveChanges();
	    Session session = Session.getInstance(props, null);
	    Transport transport = session.getTransport();
	    transport.connect(username, password);
	    System.out.println("Transport: " + transport.toString());
	    transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
	    transport.close();
	    log.debug(" send mail success");
	  }
}
