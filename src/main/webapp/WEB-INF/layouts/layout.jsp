<%@page import="java.util.List"%>
<%@page import="com.teardowall.models.WebGroup"%>
<%@page import="com.teardowall.models.WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
  <%@ include file="/WEB-INF/layouts/header.jsp"%>
  <body>
  <header class="navbar navbar-static-top bs-docs-nav top-header" id="top" role="banner">
    <div class="container">
      <a href="#" class="col-md-1 navbar-brand" target="_blank">TEARDOWALL</a>
      <label id="weatherLabel" class="col-md-10 weather-label"></label><!-- col-md-offset-9 -->
      <!-- Single button -->
      
      <div class="btn-group margin-top-15">
		<a href="javascript:;" class="dropdown-toggle font-17" id=":0" role="button" aria-haspopup="true" aria-activedescendant="" data-toggle="dropdown" aria-expanded="false">
			<img alt="" src="${ctx}/static/images/custom/config.svg" style="width: 20px;height: 20px;">
			<span class="name">${username}</span>
		</a>
		<ul class="dropdown-menu" id="top-nav-profile-dropdown" aria-labelledby=":0">
		  <li><a href="${ctx}/login">登录</a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/account/logout">登出</a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/web_group/add_group" target="_blank">增加网站组合</a></li>
		</ul>
		
	  </div>
    </div>
  </header>
    <sitemesh:body/>
    <%@ include file="/WEB-INF/layouts/footer.jsp"%>
    <div class="jump-top-box" style="right: 5px;top: 320px;position: fixed;z-index: 999;">
       <div class="jump-share" style="background: #71d38c;">
       		<div class="jump-share-tit" style="font-size: 21px;padding: 10px;"><a href="${ctx}/donate/index" style="color: white;" target="_blank">捐赠<br>计划</a></div>
       </div>
   </div>
  </body>
  <script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
  <script src="http://s1.bdstatic.com/r/www/cache/global/js/BaiduHttps_20150714_zhanzhang.js"></script>
  <!-- <script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/lib/jquery-1.10.2_d88366fd.js"></script> -->
  <script src="${ctx}/static/js/fat.js"></script>
  <!-- <script src="${ctx}/static/js/baidu.js"></script> -->
</html>