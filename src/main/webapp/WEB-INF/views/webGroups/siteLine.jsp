<%@page import="java.util.List"%>
<%@page import="com.teardowall.common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

		<div class="vertical-padding-3">
          <span>网址:</span>
          <span><input type="text" name="url" placeholder="请输入网址" value="${ site.webUrl}" class="col-md-6 bootstrap-only-with"></span>
          <span>名称:</span>
          <span><input type="text" name="name" placeholder="请输入名称" value="${ site.name}" class="col-md-2 bootstrap-only-with"></span>
          <span>序号:</span>
          <span>
            <select name="serial">
              <c:forEach begin="1" end="8" varStatus="selfStatus">
              	<c:if test="${selfStatus.index == status.count}">
              		<option value="${selfStatus.index}" selected="selected">${selfStatus.index}</option>
              	</c:if>
              	<c:if test="${selfStatus.index != status.count}">
              		<option value="${selfStatus.index}">${selfStatus.index}</option>
              	</c:if>
              </c:forEach>
            </select>
            <input type="hidden" name="icon" value="${ site.iconId}">
          </span>
        </div>