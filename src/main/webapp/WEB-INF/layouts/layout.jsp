<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>
  	 <!-- <sitemesh:title/> -->
     Teardowall
  </title>
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
	<%
	String lang = request.getLocale().getLanguage();
	if("zh".equals(lang)){
		%>
		<!-- <script src="${ctx}/static/jquery-validation/1.14.0/dist/localization/messages_zh.js" type="text/javascript"></script> -->
		<%
	}
	%>

</head>

<body>
  <div id="wrapper">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
    <div id="page-wrapper">
      <sitemesh:body/>
    </div>
  </div>
  <script type="text/javascript">
  <!-- 3秒后自动关闭成功提示框，错误的不自动关闭 -->
  //window.setTimeout(function() { $(".alert-success").alert('close'); }, 3000);
  </script>
  <%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>

</html>
