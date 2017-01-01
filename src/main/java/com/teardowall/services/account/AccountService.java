package com.teardowall.services.account;


import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teardowall.common.Common;
import com.teardowall.mail.MailHelper;
import com.teardowall.mapper.UserMapper;
import com.teardowall.models.User;
import com.teardowall.services.BaseService;


/**
 * 用户管理类.
 * 
 * @author Jerry
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService extends BaseService {
	
  public static final String HASH_ALGORITHM = "SHA-1";
  //public static final int HASH_INTERATIONS = 1024;
  //private static final int SALT_SIZE = 8;

  //private static Logger logger = LoggerFactory.getLogger(AccountService.class);
  
  @Resource
  private UserMapper userMapper;

  public User findUserByEmail(String email) {
	  User user = userMapper.findUserByEmail(email);
	  return user;
  }
  
  public void addUser(String name,String email,String password){
	  User user = new User();
	  user.setName(name);
	  user.setNickName(name);
	  user.setEmail(email);
	  user.setEmailActive(0);
	  user.setPassword(Common.encrypyPasswd(password));
	  user.setSalt(Common.generateRandom());
	  Date date = new Date();
	  user.setCreatedAt(date);
	  user.setUpdatedAt(date);
	  userMapper.addUser(user);
  }
  
  public void sendAuthenEmail(User user) throws IOException, MessagingException{
	  StringBuilder mailBody = new StringBuilder();
	  mailBody.append("***********************请点击下方链接,验证邮箱************************\n");
	  mailBody.append(Common.thisSite);
	  mailBody.append("csa/account/auth_email?auth_string=");
	  mailBody.append(Common.encrypyPasswd(user.getPassword() + user.getSalt()));
	  mailBody.append("&auth_id=");
	  mailBody.append(user.getEmail());
	  MailHelper.sendMail(user.getEmail(), user.getNickName() + ",欢迎使用Teardowall，请验证邮箱", mailBody.toString());
  }
  
  public String authEamil(String email, String saltPass){
	  User user = findUserByEmail(email);
	  if(user != null && user.getEmailActive() == 1){
		  return "已经验证,无需重复!";
	  }
	  else if(user != null && user.getEmailActive() == 0){
		  if(Common.encrypyPasswd(user.getPassword() + user.getSalt()).equals(saltPass)){
			  user.setEmailActive(1);
			  userMapper.updateUser(user);
			  return "验证成功!";
		  }
	  }
	  return "验证失败!";
  }

}
