package com.teardowall.task;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.teardowall.common.Common;
import com.teardowall.models.WebSite;
import com.teardowall.services.WebSiteService;

public class IconGenerator {
	
	Log log = LogFactory.getLog(IconGenerator.class);
	
	@Autowired
	protected WebSiteService webSiteService;
	
	public void run(){
		log.debug(generateLog(Common.date2StringByFormat(new Date(), Common.STANDARD_DATE)));
		generateIcons();
		log.debug(generateLog(Common.date2StringByFormat(new Date(), Common.STANDARD_DATE)));
	}
	
	private void generateIcons(){
		List<WebSite> sites = webSiteService.getDefaultIconSites();
		for(Iterator<WebSite> iterator = sites.iterator(); iterator.hasNext();){
			WebSite site = iterator.next();
			if(Common.stringIsEmpty(site.getWebUrl())){
				continue;
			}
			try {
				webSiteService.updateIconIdByUrl(site, captureIcon(site.getWebUrl()));
				updateSiteLog(site);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String captureIcon(String urlStr) throws IOException{
		String iconUrl = "";
		Connection conn = Jsoup.connect(urlStr);
		Document doc = conn.get();
		Elements links = doc.select("head link[href]");
		for(Iterator<Element> it = links.iterator(); it.hasNext(); ){
			Element e= it.next();
			if(e.attr("href").indexOf(".ico") >= 0){
				iconUrl = e.attr("href");
				if(iconUrl.indexOf("//") == 0){
					iconUrl = iconUrl.replace("//", "");
				}
				else if(iconUrl.indexOf("/") == 0){
					iconUrl = urlStr + iconUrl;
				}
				break;
			}
		}
		if(iconUrl.isEmpty()){
			iconUrl = urlStr + "favicon.ico";
		}
		//System.out.println(links.get(0));
		return iconUrl;
	}
	
	private void updateSiteLog(WebSite site){
		StringBuilder logString = new StringBuilder(Common.date2StringByFormat(new Date(), Common.STANDARD_DATE));
		logString.append(" ");
		logString.append(site.getId());
		logString.append(" ");
		logString.append(site.getWebUrl());
		logString.append(" ");
		logString.append(site.getIconId());
		log.debug(logString.toString());
	}
	
	private String generateLog(String log){
		StringBuilder str = new StringBuilder();
		str.append("****************************");
		str.append(log);
		str.append("****************************");
		return str.toString();
	}
	
}
