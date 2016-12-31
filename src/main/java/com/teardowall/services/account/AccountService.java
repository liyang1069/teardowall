package com.teardowall.services.account;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
