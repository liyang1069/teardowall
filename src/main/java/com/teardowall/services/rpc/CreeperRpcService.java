package com.teardowall.services.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.projectapi.teardowall.CreeperService;

@Component
@Transactional
public class CreeperRpcService {

	public String dubbo(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-dubbo.xml" });
        context.start();
  
        CreeperService creeperService = (CreeperService) context.getBean("creeperService");
        String hello = creeperService.sayHello("tom");
        System.out.println(hello);
        context.close();
        return hello;
	}
}
