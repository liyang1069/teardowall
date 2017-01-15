<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.teardowall.common.Common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="version" value="<%= Common.VERISON %>" />
<footer class="bs-docs-footer fat-footer" role="contentinfo">
  <div class="container">
    <p>
      Designed and built with all the love in the world by 
      <a href="mailto:teardowall@163.com" target="_blank">@Jason</a>
       and 
      <a href="https://twitter.com/Jerry1069li" target="_blank">@Jerry</a>.
    </p>
    <ul class="bs-docs-footer-links muted">
      <li>当前版本： v${version}</li>
      <li>·</li>
      <li><a href="https://github.com/liyang1069/teardowall">GitHub 仓库</a></li>
      <li>·</li>
      <li><a href="https://github.com/liyang1069/teardowall/blob/master/README.md">关于</a></li>
    </ul>
    <p>Anything is open source. Anything is internationalization. Anything is any persion.</p>
    <p>TEAR DOWN THE WALL.WE ALL ARE A FAMILY.</p>
  </div>
</footer>