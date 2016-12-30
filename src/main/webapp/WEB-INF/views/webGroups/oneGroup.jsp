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
    <div class="row margin-top-50">
      <div class="panel panel-default col-md-offset-1 col-md-10 no-horizontal-padding">
        <!-- Default panel contents -->
        <div class="panel-heading col-md-12">门户网站</div>

        <!-- Table -->
        <table class="table col-md-12">
          <tbody>
            <jsp:include flush="true" page="oneRow.jsp" />
            <jsp:include flush="true" page="oneRow.jsp" />
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
