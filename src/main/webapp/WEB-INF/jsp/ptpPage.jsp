<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lavra: ptp purchase page</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />" >
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div align="center" style="width: 90%;">
		<table border="1">
			<tr>
				<td>
					<img src="${pitem.item.image}" width="340" height="300">
				</td>
				<td>
					<table border="1" style="height: 300px; width: 400px;">
						<tr align="center">
							<td>상품명</td>
							<td>${pitem.item.title}</td>
						</tr>
						<tr align="center">
							<td>가격</td>
							<td><fmt:formatNumber value="${pitem.item.price}" pattern="###,###,###"/></td>
						</tr>
						<tr align="center">
							<td>상품소개</td>
							<td>${pitem.item.description}</td>
						</tr>
						<tr align="center">
							<td>배송비</td>
							<td>${pitem.devP}</td>
						</tr>
						<tr align="center">
							<td>판매자 아이디</td>
							<td>${pitem.sellerId}</td>
						</tr>
						<tr align="center">
							<td>판매자 계좌</td>
							<td>${pitem.bank} ${pitem.account}</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>