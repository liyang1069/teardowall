<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
  <body>
    <td class="col-md-3 center-align border-solid-1">
      <a href="http://www.sina.com/" target="_blank">
        <span class="margin-right-10"><img src="${ctx}/static/images/custom/default.png"></span>
        <span>腾讯新闻</span>
      </a>
    </td>
  </body>
</html>