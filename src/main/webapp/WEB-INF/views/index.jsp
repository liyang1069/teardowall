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
<head>
  <title>Teardowall</title>
  <!-- 新 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

  <!-- 可选的Bootstrap主题文件（一般不用引入） -->
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <link href="${ctx}/static/css/site.css" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/docs.min.css" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/fat.css" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/buttons.css" media="all" rel="stylesheet" type="text/css">
</head>

<body>
  <header class="navbar navbar-static-top bs-docs-nav top-header" id="top" role="banner">
    <div class="container">
      <a href="#" class="col-md-1 navbar-brand" target="_blank">TEARDOWALL</a>
      <label class="col-md-1 col-md-offset-9"></label>
      <!-- Single button -->
      <div class="btn-group margin-top-15">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        ${username}<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" style="min-width: 0;">
          <li><a href="${ctx}/login" target="_blank">登录</a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/account/logout" target="_blank">登出</a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/web_group/add_group" target="_blank">增加网站组合</a></li>
        </ul>
      </div>
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
        <%@include file="webGroups/oneGroup.jsp" %>
      </c:forEach>
      
    </div>
  </div>

  <footer class="bs-docs-footer fat-footer" role="contentinfo">
    <div class="container">
      <p>
        Designed and built with all the love in the world by 
        <a href="mailto:teardowall@163.com" target="_blank">@Jackson</a>
         and 
        <a href="https://twitter.com/Jerry1069li" target="_blank">@Jerry</a>.
      </p>
      <!-- <p>Maintained by the <a href="https://github.com/orgs/twbs/people">core team</a> with the help of <a href="https://github.com/liyang1069/teardowall/graphs/contributors">our contributors</a>.</p>
      <p>本项目源码受 <a href="https://github.com/liyang1069/teardowall/blob/master/LICENSE" target="_blank">MIT</a> 开源协议保护，文档受 <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a> 开源协议保护。</p> -->
      <ul class="bs-docs-footer-links muted">
        <li>当前版本： v1.0.0</li>
        <li>·</li>
        <li><a href="https://github.com/liyang1069/teardowall">GitHub 仓库</a></li>
        <li>·</li>
        <li><a href="https://github.com/liyang1069/teardowall/blob/master/README.md">关于</a></li>
      </ul>
      <p>Anything is open source. Anything is internationalization. Anything is any persion.</p>
      <p>TEAR DOWN THE WALL.WE ALL ARE A FAMILY.</p>
    </div>
  </footer>
</body>
<script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
<script src="http://s1.bdstatic.com/r/www/cache/global/js/BaiduHttps_20150714_zhanzhang.js"></script>
<!-- <script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/lib/jquery-1.10.2_d88366fd.js"></script> -->
<script src="${ctx}/static/js/fat.js"></script>
<!-- <script src="${ctx}/static/js/baidu.js"></script> -->
</html>
