package com.teardowall.services.rpc;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.projectapi.teardowall.CreeperService;
import com.projectapi.teardowall.entity.LocationTmp;
import com.projectapi.teardowall.entity.Weather;
import com.projectapi.teardowall.entity.WeatherBaidu;
import com.teardowall.common.Common;

@Component
//@Transactional
public class CreeperRpcService {

	public String dubbo(){
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-dubbo.xml" });
//        context.start();
//  
//        CreeperService creeperService = (CreeperService) context.getBean("creeperService");
		CreeperService creeperService = (CreeperService) Common.getBean("creeperService");
        String hello = creeperService.sayHello("tom");
        System.out.println(hello);
        //context.close();
        return hello;
	}
	
	public String getWeather(String city){
		CreeperService creeperService = (CreeperService) Common.getBean("creeperService");
		String weather = creeperService.catchBaiduWeather(city);
		return weather;
	}
	
	public LocationTmp getLocation(String ip){
		CreeperService creeperService = (CreeperService) Common.getBean("creeperService");
		return creeperService.getLocationFromIp(ip);
	}
}
