package com.teardowall.services.account;


import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.projectapi.teardowall.entity.LocationTmp;
import com.projectapi.teardowall.entity.Weather;
import com.projectapi.teardowall.entity.WeatherBaidu;
import com.teardowall.common.Common;
import com.teardowall.common.Redis;
import com.teardowall.mail.MailHelper;
import com.teardowall.mapper.UserMapper;
import com.teardowall.models.User;
import com.teardowall.services.BaseService;
import com.teardowall.services.rpc.CreeperRpcService;


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
  
  @Resource
  private CreeperRpcService creeperRpcService;

  public User findUserByEmail(String email) {
	  User user = userMapper.findUserByEmail(email);
	  return user;
  }

  public User findUserById(String id) {
	  User user = userMapper.findUserById(id);
	  return user;
  }
  
  public void addUser(String name,String email,String password){
	  User user = new User();
	  user.setName(name);
	  user.setNickName(name);
	  user.setEmail(email);
	  user.setEmailActive(0);
	  user.setSalt(Common.generateRandom());
	  user.setPassword(Common.encrypyPasswd(password + Common.PASSWD_SUFFIX + user.getSalt()));
	  Date date = new Date();
	  user.setCreatedAt(date);
	  user.setUpdatedAt(date);
	  userMapper.addUser(user);
  }
  
  public void updateUserLocation(LocationTmp location,User user, String ip){ 
	  user.setCityName(location.getContent().getAddress_detail().getCity());
	  user.setLongitude(location.getContent().getPoint().getX());
	  user.setLatitude(location.getContent().getPoint().getY());
	  user.setIp(ip);
	  userMapper.updateUser(user);
  }
  
  public String getWeatherByIp(String ip, String userId){
	  if(Common.stringIsEmpty(ip))
		  return null;
	  User user = findUserById(userId);
	  if(!ip.equals(user.getIp())){
		  updateUserLocation(creeperRpcService.getLocation(ip), user, ip);
	  }
	  return creeperRpcService.getWeather(user.getCityName());
  }
  
  public String generateWeatherString(WeatherBaidu weather){
	  StringBuilder weatherString = new StringBuilder();
	  if (weather == null){
		  return weatherString.toString();
	  }
	  weatherString.append(weather.getCityName());
	  weatherString.append(": ");
	  weatherString.append(weather.getWeatherDate());
	  weatherString.append(" ");
	  weatherString.append(weather.getWeather());
	  weatherString.append(" ");
	  weatherString.append(weather.getWind());
	  weatherString.append(" ");
	  weatherString.append(weather.getTemperature());
	  return weatherString.toString();
  }
  
  public void sendAuthenEmail(User user) throws IOException, MessagingException{
	  StringBuilder mailBody = new StringBuilder();
	  mailBody.append("***********************请点击下方链接,验证邮箱************************\n<br/>");
	  mailBody.append(Common.thisSite);
	  mailBody.append("/account/auth_email?auth_string=");
	  mailBody.append(Common.encrypyPasswd(user.getPassword() + user.getSalt()));
	  mailBody.append("&auth_id=");
	  mailBody.append(user.getEmail());
	  MailHelper.sendMail(user.getEmail(), user.getNickName() + ",欢迎使用Teardowall，请验证邮箱", mailBody.toString());
  }
  
  public int authEamil(User user, String saltPass){
	  if(user != null && user.getEmailActive() == 1){
		  return 0;
	  }
	  else if(user != null && user.getEmailActive() == 0){
		  if(Common.encrypyPasswd(user.getPassword() + user.getSalt()).equals(saltPass)){
			  user.setEmailActive(1);
			  userMapper.updateUser(user);
			  return 1;
		  }
	  }
	  return 2;
  }
  
  public boolean authCookies(String userId, String userName){
	  if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName) || "0".equals(userId))
		  return false;
	  StringBuilder keyB = new StringBuilder("userId");
	  keyB.append(userId);
	  Jedis jedis = Redis.getJedis();
	  if(jedis.exists(keyB.toString()) && userName.equals(jedis.get(keyB.toString()))){
		  Redis.returnResource(jedis);
		  return true;
	  }
	  User user = findUserById(userId);
	  if (user == null || !userName.equals(user.getName()))
		  return false;
	  jedis.set(keyB.toString(), userName);
	  Redis.returnResource(jedis);
	  System.out.println("==========================================================================");
	  return true;
  }

}
