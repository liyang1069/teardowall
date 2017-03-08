<%@page import="java.util.List"%>
<%@page import="com.teardowall.models.WebGroup"%>
<%@page import="com.teardowall.models.WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>

<body>
  <%-- <header class="navbar navbar-static-top bs-docs-nav top-header" id="top" role="banner">
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
  </header> --%>
  
  <div class="site-header jumbotron sem-div">
    <div class="container">
      <div class="row sem-row">
        <div class="col-md-12" id="google_div">
          <!-- <h1 id="google_h1" class="padding-bottom-30">Google</h1> -->
          <img src="${ctx}/static/images/logo/google_logo.svg" class="sem-svg">
          <form action="http://www.google.com/search" target="_blank">
            <input name="ie" value="UTF-8" type="hidden" />
            <input type="text" class="form-control search clearable" name="q" size="30">
          </form>
        </div>
        <div class="col-md-12 hide" id="baidu_div">
          <!-- <h1 id="baidu_h1" class="padding-bottom-30">Baidu</h1> -->
          <img src="${ctx}/static/images/logo/baidu_logo.svg" class="sem-svg">
          <form onsubmit="return baidu_with_https(this)" action="http://www.baidu.com/baidu" target="_blank">
            <input name="tn" type="hidden" value="SE_zzsearchcode_shhzc78w">
            <input type="text" class="form-control search clearable" onfocus="checkHttps" name="word" size="30" baiduSug="1" id="baidu_input">
            <span id="alertSpan" style="font-weight:bold"></span>
          </form>
        </div>
        <div class="col-md-12 hide" id="bing_div">
          <!-- <h1 id="bing_h1" class="padding-bottom-30">Bing</h1> -->
          <img src="${ctx}/static/images/logo/bing_logo.svg" class="sem-svg">
          <form action="http://cn.bing.com/search" target="_blank" id="sb_form">
              <input class="b_searchbox form-control search clearable" id="sb_form_q" name="q" title="输入搜索词" type="search" value="" maxlength="100" onfocus="_ge('b_header').className='b_focus';" dir="" autocapitalize="off" autocorrect="off" autocomplete="off" spellcheck="false">
              <input id="sa_qs" name="qs" value="bs" type="hidden">
              <input type="hidden" value="QBRE" name="form">
          </form>
        </div>
        <div class="col-md-12 padding-top-20">
          <p class="">
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-2 col-md-2"  id="google_btn">Google</a>
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-1 col-md-2"  id="baidu_btn">Baidu</a>
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-1 col-md-2"  id="bing_btn">Bing</a>
          </p>
        </div>
      </div>
    </div>
  </div>

  <div class="common-icon no_display">
    <p class="col-md-12 ">
      <span class="col-md-1 col-md-offset-2"><a href="http://www.weibo.com/?c=spr_sinamkt_buy_9973dh_weibo_t001" target="_blank" class="common-svg common-weibo "></a></span>
      <span class="col-md-1"><a href="https://www.zhihu.com/" target="_blank" class="common-svg common-zhihu"></a></span>
      <span class="col-md-1"><a href="http://email.163.com/" target="_blank" class="common-svg common-163"></a></span>
      <span class="col-md-1"><a href="http://www.12306.cn/mormhweb/" target="_blank" class="common-svg common-12306"></a></span>
      <span class="col-md-1"><a href="https://github.com/" target="_blank" class="common-svg common-github"></a></span>
      <span class="col-md-1"><a href="https://www.facebook.com/" target="_blank" class="common-svg common-facebook"></a></span>
      <span class="col-md-1"><a href="https://twitter.com/" target="_blank" class="common-svg common-twitter"></a></span>
      <span class="col-md-1"><a href="https://www.tumblr.com/" target="_blank" class="common-svg common-tumblr"></a></span>
    </p>
  </div>
  <div class="mostused-packages well-sm text-center hidden-xs hidden-sm">
    <ul class="list-inline">
      <li><a href="http://www.weibo.com/?c=spr_sinamkt_buy_9973dh_weibo_t001" target="_blank" class="common-svg common-weibo "></a></li>
      <li><a href="https://www.zhihu.com/" target="_blank" class="common-svg common-zhihu"></a></li>
      <li><a href="http://email.163.com/" target="_blank" class="common-svg common-163"></a></li>
      <li><a href="http://www.12306.cn/mormhweb/" target="_blank" class="common-svg common-12306"></a></li>
      <li><a href="https://github.com/" target="_blank" class="common-svg common-github"></a></li>
      <li><a href="https://www.facebook.com/" target="_blank" class="common-svg common-facebook"></a></li>
      <li><a href="https://twitter.com/" target="_blank" class="common-svg common-twitter"></a></li>
      <li><a href="https://www.tumblr.com/" target="_blank" class="common-svg common-tumblr"></a></li>
    </ul>
  </div>

  <div class="web-groups">
    <div class="container bs-docs-container web-content">
      <c:forEach items="${ groups}" var="group" varStatus="status">
        <c:set var="ws" value="${ sites.get(status.index) }"></c:set>
        <%@include file="webGroups/oneGroup.jsp" %>
      </c:forEach>
      
    </div>
  </div>
<script type="text/javascript">
	setInterval(getWeather(), 1800000);
	function getWeather(){
		$.ajax({
			url : "${ctx}/web_group/get_weather",
            type : "GET",
            dataType : 'text',
            success : function(data){
              $("#weatherLabel").html(data);
            },
            error : function(){
              
            }
		});
	}
</script>
</body>
<script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
<script src="http://s1.bdstatic.com/r/www/cache/global/js/BaiduHttps_20150714_zhanzhang.js"></script>
<!-- <script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/lib/jquery-1.10.2_d88366fd.js"></script> -->
<script src="${ctx}/static/js/fat.js"></script>
<!-- <script src="${ctx}/static/js/baidu.js"></script> -->
</html>
