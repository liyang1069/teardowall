<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@page import="com.teardowall.models.WebGroup"%>
<%@page import="com.teardowall.models.WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<body>
  <header class="navbar navbar-static-top bs-docs-nav top-header" id="top" role="banner">
    <div class="container">
      <a href="#" class="col-md-1 navbar-brand" target="_blank">TEARDOWALL</a>
      <label class="col-md-1 col-md-offset-7 no_display">Teardowall</label>
    </div>
  </header>
  
  <div class="site-header jumbotron sem-div">
    <div class="container">
      <div class="row sem-row">
        <div class="col-md-12" id="google_div">
          <h1 id="google_h1" class="padding-bottom-30">Google</h1>
          <form action="http://www.google.com/search" target="_blank">
            <input name="ie" value="UTF-8" type="hidden" />
            <input type="text" class="form-control search clearable" name="q" size="30">
          </form>
        </div>
        <div class="col-md-12 hide" id="baidu_div">
          <h1 id="baidu_h1" class="padding-bottom-30">Baidu</h1>
          <form onsubmit="return baidu_with_https(this)" action="http://www.baidu.com/baidu" target="_blank">
            <input name="tn" type="hidden" value="SE_zzsearchcode_shhzc78w">
            <input type="text" class="form-control search clearable" onfocus="checkHttps" name="word" size="30" baiduSug="1" id="baidu_input">
            <span id="alertSpan" style="font-weight:bold"></span>
          </form>
        </div>
        <div class="col-md-12 hide" id="bing_div">
          <h1 id="bing_h1" class="padding-bottom-30">Bing</h1>
          <form action="http://cn.bing.com/search" target="_blank" id="sb_form">
              <input class="b_searchbox form-control search clearable" id="sb_form_q" name="q" title="输入搜索词" type="search" value="" maxlength="100" onfocus="_ge('b_header').className='b_focus';" dir="" autocapitalize="off" autocorrect="off" autocomplete="off" spellcheck="false">
              <input id="sa_qs" name="qs" value="bs" type="hidden">
              <input type="hidden" value="QBRE" name="form">
          </form>
        </div>
        <div class="col-md-12 padding-top-50">
          <p class="">
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-2 col-md-2"  id="google_btn">Google</a>
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-1 col-md-2"  id="baidu_btn">Baidu</a>
            <a href="javascript:void(0)" class="button button-glow button-border button-rounded button-primary col-md-offset-1 col-md-2"  id="bing_btn">Bing</a>
          </p>
        </div>
      </div>
    </div>
  </div>

  <div class="common-icon">
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

  <div class="web-groups">
    <div class="container bs-docs-container web-content">
      <c:forEach items="${ groups}" var="group" varStatus="status">
        <c:set var="ws" value="${ sites.get(status.index) }"></c:set>
        <%@include file="oneGroup.jsp" %>
      </c:forEach>
      
    </div>
  </div>
</body>
</html>