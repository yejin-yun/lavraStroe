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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />" >
</head>
<body>
<%@ include file="header.jsp" %>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:if test="${pitem eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">상품 로딩 중 문제가 발생하였습니다.</div>
</c:if>	
<div class="w3-center" style="width: 80%; margin : auto;">
	<table class="table table-bordered">
		<tr>
			<td rowspan="8">
				<img src="${pitem.item.image}" width="340" height="300">
			</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td>${pitem.item.title}</td>
		</tr>
		<tr align="center">
			<td> 가격 </td>
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
		
		<tr align="center">
			<td colspan="2">
				<form name="form1" method="post" action="<c:url value='/accessory/newPtpOrder.do'/>">
					<input type="hidden" name="itemId" value="${pitem.item.itemId}">
					<input type="hidden" name="sellerId" value="${pitem.item.itemId}">
					<c:if test="${pitem.state == 0}"> <!-- 진행 중인 구매 가능 -->
						<input type="submit" value="구매하기">
					</c:if>
					<c:if test="${pitem.state != 0}">
						<input type="submit" value="구매하기" disabled>
					</c:if>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>