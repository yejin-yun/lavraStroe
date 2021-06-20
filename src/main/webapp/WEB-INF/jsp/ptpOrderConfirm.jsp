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
<meta charset="UTF-8">
<title>개인 구매 확인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
	.interval {
			margin: 3% 0;
		}
	.funcs {
		margin-top: 5%;
		margin-bottom: 10%;
	}
	.funcs input
	{
		margin-right: 10px;
		padding: 5px;
		background-color: white;
		border: 1px solid #646EFF;
		color: #646EFF;
		border-top-left-radius: 5px; 
		border-bottom-left-radius: 5px;
		border-top-right-radius: 5px; 
       	border-bottom-right-radius: 5px;
     }
	.funcs input:hover
	{ 	
		color:white; 
		background-color: #646EFF; 
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<br>
<br>

<c:if test="${success == true}">
	<div class = "container">
		<div class="w3-center">
			<h2> ${myOrder.memberId}님 항상 Lavra를 이용해주셔서 감사합니다! </h2>
			<p>[주문번호 : <strong>${myOrder.orderId}</strong>]가 성공적으로 신청되었습니다.</p>
		</div>
		
		<br>
		<div class="w3-center">
			<c:set var="targetUrl"><c:url value="/"/></c:set>
			<form name=form action="${targetUrl}" method="post" style="margin: 0px auto;">
				<input type="submit" value="메인 메뉴로 이동">
			</form>
		</div>
	</div>
</c:if>
</body>
</html>