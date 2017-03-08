<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>

<body>
  
  
  <div class="col-md-12">
  	<div class="jumbotron jumbotron-div" style="padding: 50px;">
	  	<h1>Hello, everyone!</h1>
		<p style="margin-top: 20px; font-size: 14;">本网站是唯一自定义式书签导航网站，为用户提供免费服务。</p>
		<p style="font-size: 14;">商业本无过，广告亦非恶。但它们不属于这里。不属于我们。</p>
		<p style="font-size: 14;">我们与众不同，它像一座图书馆或者一座公园，它像我们的家，自己打理的地方。</p>
		<p style="font-size: 14;">在初创时，我完全可以让它成为一家刊登广告的营利性网站，但是我决定做与众不同的事情。我们一直致力于保证其精简和节流。我们完成我们的使命，避免铺张浪费。</p>
		<p style="font-size: 14;">如果读到这封信的人都愿意捐出¥50，那么我们筹款一月就可维持一年的开销。但并不是每个人都有能力或者有意愿捐款。这可以理解。好在每一天都会刚好有足够多的人愿意伸出援手。</p>
		<p style="font-size: 14;">今年，请您考虑捐献任何您力所能及的金额，来保障和维持网站的运作。</p>
		<p style="font-size: 14;">感激不尽！</p>
		<p style="font-size: 14;">Jerry</p>
		<p style="font-size: 14;">TEARDOWALL FOUNDER</p>
		<p></p>
	</div>
	<div class="panel panel-primary panel-pay">
      <div class="panel-heading">
        <h3 class="panel-title">捐赠方式</h3>
      </div>
      <div class="panel-body" style="text-align: center;">
        <img alt="" src="${ctx}/static/images/custom/zhifubao.JPG" class="zhidubao-img">
        <img alt="" src="${ctx}/static/images/custom/weixin.JPG" class="weixin-img">
      </div>
    </div>
  	<table class="table table-bordered table-striped donate-tabel">
		<thead>
			<tr>
				<th>序号</th>
				<th>捐赠者</th>
				<th>金额</th>
				<th>日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="donate" items="${donateInfos}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${donate.donatorName}</td>
					<td>${donate.amount}</td>
					<td><fmt:formatDate value="${donate.createdAt}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  </div>
</body>
</html>
