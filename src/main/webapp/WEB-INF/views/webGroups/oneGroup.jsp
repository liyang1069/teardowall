<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@page import="com.teardowall.models.WebGroup"%>
<%@page import="com.teardowall.models.WebSite" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


    <div class="row margin-top-50">
      <div class="panel panel-default col-md-offset-1 col-md-10 no-horizontal-padding">
        <!-- Default panel contents -->
        <div class="panel-heading col-md-12">
        	<a target="_blank" class="black-a" href="${ ctx}/web_group/${group.id}/web_config">${ group.name }</a>
        </div>

        <!-- Table -->
        <table class="table col-md-12">
          <tbody>
            <%-- <jsp:include flush="true" page="oneRow.jsp" />
            <jsp:include flush="true" page="oneRow.jsp" /> --%>
            <c:set var="subSites" value="${ws.subList(0,4)}"></c:set>
            <%@include file="oneRow.jsp" %>
            <c:if test="${ ws.size() > 4}">
            	<c:set var="subSites" value="${ws.subList(4,8)}"></c:set>
            	<%@include file="oneRow.jsp" %>
            </c:if>
          </tbody>
        </table>
      </div>
    </div>
