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
	.info {
		width : 90%;
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
		
		<div class="info">
			<h3><b>| 상품 정보 :</b></h3>
			<div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>상품명</th>
							<th>가격</th>
							<th>배송비</th>
							<th>합계</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<img style="height: 120px; src='${pItem.item.image}' />" />
							</td>
							<td>
								${pItem.item.title }
							</td>
							<td>
								<fmt:formatNumber value="${pItem.item.price }" pattern="###,###,###"/>원
							</td>
							<td>
								<fmt:formatNumber value="${pItem.devP }" pattern="###,###,###"/>원
							</td>
							<td>
								<fmt:formatNumber value="${myOrder.totalPrice}" pattern="###,###,###"/>원
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<br>
		<div class="info">
			<h3><b>| 배송 정보 :</b></h3>
			<div >
			<ul class="list-group list-group-flush" >
				<li class="list-group-item" > 
					<label>받는분 : </label> &nbsp; &nbsp; &nbsp; &nbsp; ${myOrder.shipname}
				</li>
				<li class="list-group-item" >
					<label>주소 :</label> &nbsp; &nbsp; &nbsp; &nbsp; ${myOrder.shipAddr1 } ${myOrder.shipAddr2 }
				</li> 
			</ul>
		</div>
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