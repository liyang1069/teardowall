package com.teardowall.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
	public static String icon_path_relative = "/static/icon/";
	public static String icon_path_absolute = "/Users/jerry.li/projects/java/cp_file/origin_project/src/main/webapp"+icon_path_relative;
	public static int[] testArray = {1,2,3,4};
	public static String sites = "新浪,http://www.sina.com.cn/,com_sina_news;腾讯,http://www.qq.com/,com_tencent_news;淘宝,https://www.taobao.com/,com_taobao_taobao;京东,https://www.jd.com/,com_jingdong_app_mall;爱奇艺,http://www.iqiyi.com/,com_qiyi_video;优酷,http://www.youku.com/,com_youku_phone;去哪儿,https://www.qunar.com/,com_qunar;拉钩网,https://www.lagou.com/,com_job_android";
	public static final String thisSite = "http://localhost:8080/csa";
	//public static final String thisSite = "http://123.57.247.225:8080/teardowall";
	public static final String defaultIconPath = "/static/images/custom/default.png";
	public static final String passwdSuffix = "suffix";
	
	public static String encrypyPasswd(String passwd){
		byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            // 添加要进行计算摘要的信息
            alga.update(passwd.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
	}
	
	public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
	
	public static boolean verifyEmail(String email){
	      String pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(email);
	      if (m.find( )) {
	         return true;
	      } else {
	         return false;
	      }
	}
	
	public static int generateRandom(int scope){
		return new Random().nextInt(scope);
	}
	
	public static int generateRandom(){
		return generateRandom(10000);
	}
	
	public static boolean stringIsEmpty(String str){
		if(str == null || str.isEmpty() || str.equals("")){
			return true;
		}
		return false;
	}
}
