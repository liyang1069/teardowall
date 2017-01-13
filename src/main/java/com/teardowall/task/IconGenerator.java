package com.teardowall.task;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teardowall.common.Common;
import com.teardowall.models.WebSite;
import com.teardowall.services.WebSiteService;

public class IconGenerator {
	
	Log log = LogFactory.getLog(IconGenerator.class);
	
	@Resource
	private WebSiteService webSiteService;
	
	public void run(){
		log.debug(generateLog(Common.date2StringByFormat(new Date(), Common.STANDARD_DATE)));
		List<WebSite> sites = webSiteService.getDefaultIconSites();
		for(Iterator<WebSite> iterator = sites.iterator(); iterator.hasNext();){
			WebSite site = iterator.next();
			if(Common.stringIsEmpty(site.getWebUrl())){
				continue;
			}
			
		}
	}
	
	public String captureIcon(String urlStr) throws IOException{
		
		return "";
	}
	
	private String generateLog(String log){
		StringBuilder str = new StringBuilder();
		str.append("****************************");
		str.append(log);
		str.append("****************************");
		return str.toString();
	}
}
