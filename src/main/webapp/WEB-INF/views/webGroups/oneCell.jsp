<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

	<c:if test="${status.index != 3}">
    	<td class="col-md-3 center-align website-td border-solid-1">
	</c:if>
	<c:if test="${status.index == 3}">
    	<td class="col-md-3 center-align website-td border-solid-1" style="border-right: 0px;">
	</c:if>
      <a href="${site.webUrl}" class="website" target="_blank">
        <span class="margin-right-10">
        	<c:if test="${site.iconPath != null && site.iconPath.length() > 0 }">
        		<c:if test="${fn:contains(site.iconPath, 'http://') || fn:contains(site.iconPath, 'https://')}">
        			<img class="content-img" src="${site.iconPath}">
        		</c:if>
        		<c:if test="${(fn:contains(site.iconPath, 'http://') || fn:contains(site.iconPath, 'https://')) == false}">
        			<img class="content-img" src="${ctx}${site.iconPath}">
        		</c:if>
        	</c:if>
        </span>
        <span class="site-label">${site.name}</span>
      </a>
    </td>
