<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


    <tr>
      <%-- <c:forEach begin="1" end="4"> --%>
      <c:forEach items="${subSites}" var="site" varStatus="status">
        <%-- <jsp:include page="oneCell.jsp" flush="true"/> --%>
        <%@include file="oneCell.jsp" %>
      </c:forEach>
    </tr>
