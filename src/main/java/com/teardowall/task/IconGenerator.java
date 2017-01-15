package com.teardowall.task;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.teardowall.common.Common;
import com.teardowall.models.WebSite;
import com.teardowall.services.WebSiteService;

public class IconGenerator {
	
	Log log = LogFactory.getLog(IconGenerator.class);
	
	//@Autowired
	private WebSiteService webSiteService;
	
	public void run(){
		log.debug(generateLog(Common.date2StringByFormat(new Date(), Common.STANDARD_DATE)));
		boolean bool = true;
		if(bool){
			return;
		}
		List<WebSite> sites = webSiteService.getDefaultIconSites();
		for(Iterator<WebSite> iterator = sites.iterator(); iterator.hasNext();){
			WebSite site = iterator.next();
			if(Common.stringIsEmpty(site.getWebUrl())){
				continue;
			}
			try {
				captureIcon(site.getWebUrl());
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
				break;
			}
		}
		if(iconUrl.isEmpty()){
			iconUrl = urlStr + "favicon.ico";
		}
		System.out.println(links.get(0));
		return iconUrl;
	}
	
	private String generateLog(String log){
		StringBuilder str = new StringBuilder();
		str.append("****************************");
		str.append(log);
		str.append("****************************");
		return str.toString();
	}
}
