<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


    <td class="col-md-3 center-align border-solid-1">
      <a href="${site.webUrl}" target="_blank">
        <span class="margin-right-10">
        	<c:if test="${site.iconPath != null && site.iconPath.length() > 0 }">
        		<img class="content-img" src="${ctx}${site.iconPath}">
        	</c:if>
        </span>
        <span class="site-label">${site.name}</span>
      </a>
    </td>
