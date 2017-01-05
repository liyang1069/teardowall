<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="${ctx}/web_group/update" method="post">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">编辑: <input type="text" name="webGroupName" value="${webGroupName}"></h3>
      </div>
      <div class="panel-body">
        <input type="hidden" name="webGroupId" value="${webGroupId}">
        <c:forEach items="${ sites}" var="site" varStatus="status">
        	<%@include file="siteLine.jsp" %>
        </c:forEach>
      </div>
    </div>
    <div class="padding-left-20"><input type="submit"></div>
  </form>
</body>
</html>