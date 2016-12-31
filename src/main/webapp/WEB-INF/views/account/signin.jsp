<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
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
  <link href="${ctx}/static/css/site.css?id=909" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/docs.min.css?id=919" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/fat.css?id=99" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/buttons.css?id=99" media="all" rel="stylesheet" type="text/css">
  <link href="${ctx}/static/css/login.min.css?id=11" media="all" rel="stylesheet" type="text/css">
</head>
<body class="home-template">
  <header class="navbar navbar-static-top bs-docs-nav top-header" id="top" role="banner">
    <div class="container">
      <a href="#" class="col-md-1 navbar-brand">TEARDOWALL</a>
      <label class="col-md-1 col-md-offset-7 no_display">Teardowall</label>
    </div>
  </header>
  <div class="index-main">
    <div class="index-main-body sign-body">
      <div class="index-header">
        <h1 class="login-h1">TEARDOWALL</h1>
        <h2 class="subtitle">TEAR DOWN THE WALL</h2>
      </div>

      <div class="desk-front sign-flow clearfix sign-flow-simple">

        <div class="index-tab-navs">
          <div class="navs-slider" data-active-index="0">
            <a href="javascript:void(0)" class="signup-a active">注册</a>
            <a href="javascript:void(0)" class="signin-a">登录</a>
            <span class="navs-slider-bar"></span>
          </div>
        </div>


      <div class="view view-signin" data-za-module="SignInForm">
        <form method="POST" novalidate="novalidate" action="${ctx}/account/signin">
          <div class="group-inputs">

            <div class="account input-wrapper">

            <input type="text" name="account" aria-label="邮箱" placeholder="邮箱" required="">
            </div>
            <div class="verification input-wrapper">
            <input type="password" name="password" aria-label="密码" placeholder="密码" required=""><button type="button" class="send-code-button">获取验证码</button>
            </div>

            <div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha" style="display: none;">
            <div class="Captcha-operate">
            <input type="hidden" name="captcha" required="" data-rule-required="true" data-msg-required="请点击图中倒立的文字" value="">
            <input type="hidden" name="captcha_type" value="cn" required="">
            <label class="Captcha-prompt">请点击图中倒立的文字</label>
            <span class="Captcha-refresh js-refreshCaptcha sprite-index-icon-refresh"></span>
            </div>
            <div class="Captcha-imageConatiner">
            <img class="Captcha-image" alt="验证码" src="" style="display: block;">
            </div>
            </div>

          </div>
          <div class="button-wrapper command">
            <button class="sign-button submit" type="submit">登录</button>
          </div>
          <div class="signin-misc-wrapper clearfix">

            <button type="button" class="signin-switch-button no_display">手机验证码登录</button>

            <a class="unable-login" href="#">无法登录？</a>
          </div>

          <div class="social-signup-wrapper no_display" data-za-module="SNSSignIn">
            <span class="name js-toggle-sns-buttons">社交帐号登录</span>

            <div class="sns-buttons">
            <a title="微信登录" class="js-bindwechat" href="#"><i class="sprite-index-icon-wechat"></i></a>
            <a title="微博登录" class="js-bindweibo" href="#"><i class="sprite-index-icon-weibo"></i></a>
            <a title="QQ 登录" class="js-bindqq" href="#"><i class="sprite-index-icon-qq"></i></a>
            </div>

          </div>

        </form>

        </div>

        <div class="view view-signup selected" data-za-module="SignUpForm">
        <form class="zu-side-login-box" action="${ctx}/account/signup" id="sign-form-1" autocomplete="off" method="POST" novalidate="novalidate">
        <input type="password" hidden=""> 
        <div class="group-inputs">


        <div class="name input-wrapper">
        <input required="" type="text" name="fullname" aria-label="姓名" placeholder="姓名">
        </div>
        <div class="email input-wrapper">

        <input required="" type="text" class="account" name="phone_num" aria-label="邮箱" placeholder="邮箱">

        </div>

        <div class="input-wrapper">
        <input required="" type="password" name="password" aria-label="密码" placeholder="密码（不少于 6 位）" autocomplete="off">
        </div>

        <div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">
        <div class="Captcha-operate">
        <input type="hidden" name="captcha" required="" data-rule-required="true" data-msg-required="请点击图中倒立的文字" value="">
        <input type="hidden" name="captcha_type" value="cn" required="">
        <label class="Captcha-prompt">请点击图中倒立的文字</label>
        <span class="Captcha-refresh js-refreshCaptcha sprite-index-icon-refresh"></span>
        </div>
        <div class="Captcha-imageConatiner">
        <img class="Captcha-image" alt="验证码" src="" style="display: block;">
        </div>
        </div>

        </div>
        <div class="button-wrapper command">
        <button class="sign-button submit" type="submit">注册</button>
        </div>
        </form>

        </div>
      </div>
    </div>

  </div>
  <footer class="bs-docs-footer fat-footer" role="contentinfo">
    <div class="container">
      <p>
        Designed and built with all the love in the world by 
        <a href="https://twitter.com/mdo" target="_blank">@Jackson</a>
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
<script type="text/javascript">
  $(function(){
    nav_a_click();
  });
  function nav_a_click(){
    if (window.location.href.match(/page=signup/)) {
      signup_fun();
    }
    else{
      signin_fun();
    }
  }
  function signup_fun(){
    $(".view-signin").removeClass("selected");
    $(".view-signup").addClass("selected");
    $(".navs-slider").attr("data-active-index",0);
    $(".signup-a").addClass("active");
    $(".signin-a").removeClass("active");
  }
  function signin_fun(){
    $(".view-signin").addClass("selected");
    $(".view-signup").removeClass("selected");
    $(".navs-slider").attr("data-active-index",1);
    $(".signup-a").removeClass("active");
    $(".signin-a").addClass("active");
  }
  // $(".navs-slider").click(function(){
  //   nav_a_click();
  // });
  $(".signup-a").click(function(){
    signup_fun();
  });
  $(".signin-a").click(function(){
    signin_fun();
  });
</script>
</html>