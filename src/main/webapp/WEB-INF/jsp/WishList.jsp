<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'><b>
      <font color="black" size="2">&lt;&lt; Main Menu</font></b></a></td>
  </tr>
</table>

<div align="center">
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td>&nbsp;</td>
      <td><b>Product ID</b></td>
      <td><b>Name</b></td>
    </tr>
    <c:forEach var="wishlist" items="${wishlist.pageList}">
      <tr bgcolor="#FFFF88">
        <td><a
          href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${wishlist.productId}"/></c:url>'>
            <c:out value="${product.description}" escapeXml="false" />
        </a></td>
        <td><b><a
            href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${wishlist.productId}"/></c:url>'>
              <font color="BLACK"><c:out value="${wishlist.productId}" /></font>
          </a></b></td>
        <td><c:out value="${product.name}" /></td>
      </tr>
    </c:forEach>
    <tr>
      <td><c:if test="${!wishList.firstPage}">
          <a href="?page=previous"><font color="white"><B>&lt;&lt;
                Prev</B></font></a>
        </c:if> <c:if test="${!wishList.lastPage}">
          <a href="?page=next"><font color="white"><B>Next
                &gt;&gt;</B></font></a>
        </c:if></td>
    </tr>
  </table>
</div>

</body>
</html>