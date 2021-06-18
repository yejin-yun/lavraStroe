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
<title> 공동구매 신청 확인 페이지</title>
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

<br/>
<br/>
<br/>
<br/>

<c:if test="${success == true}">

<div class="container">
	<div class="w3-center">
	  <h2> ${myOrder.memberId}님 항상 Lavra를 이용해주셔서 감사합니다! </h2>
	  <p>[주문번호 : <strong>${myOrder.orderId}</strong>]가 성공적으로 신청되었습니다.</p>
	</div>
	
	<div class="info">
		<h3> <b>| 상품 정보 :</b></h3>
		<div>
		<table class="table table-striped" >
			 <thead>
			      <tr>
			        <th></th>
			        <th>상품명</th>
			        <th>가격</th>
			        <th>수량</th>
			        <th>합계</th>
			      </tr>
	    	</thead>
	    	<tr>
	   			<td>
	   				<a href="<c:url value='/group/viewItem'>
	           			<c:param name='itemNo' value='${gItem.item.itemId}' /> </c:url>"> 
	               		<img style="height: 120px;" src="<c:url value='${gItem.item.image}' />" />
	               	</a>
	   			</td>
	   			<td>
	   				<a href="<c:url value='/group/viewItem'>
	           			<c:param name='itemNo' value='${gItem.item.itemId}' /> </c:url>"> 
	            		${gItem.item.title}
	            	</a>
	            </td>
	   			<td>
	   				<fmt:formatNumber value="${gItem.item.price}" pattern="###,###,###"/>원
	   			</td>
	   			<td> ${groupOrder.quantity}</td> 
	   			<td>
	   				<fmt:formatNumber value="${myOrder.totalPrice}" pattern="###,###,###"/>원
	   			</td>
	   		</tr>
		</table>
		</div>
	</div>
	<br/>
	<div class="info">
		<h3> <b>| 배송 정보 :</b></h3>
		<div >
			<ul class="list-group list-group-flush" >
				<li class="list-group-item" > 
					<label>받는분 : </label> &nbsp; &nbsp; &nbsp; &nbsp; ${myOrder.shipname}
				</li>
				<li class="list-group-item" >
					<label>주소 :</label> &nbsp; &nbsp; &nbsp; &nbsp; ${myOrder.shipAddr1 } ${myOrder.shipAddr2 }
				</li> 
				<li class="list-group-item" >
					<label>배송 예정일 :</label> &nbsp; &nbsp; &nbsp; &nbsp;  공동구매의 경우, 목표 금액 달성 시 결제 예정일날 결제되어 이후 2~3일 내로 출고됩니다.
				</li> 
			</ul>
		</div>
	</div>
	<br/>
	<div class="info">
		<h3> <b>| 결제 정보 :</b> </h3>
		<div>
			<ul class="list-group list-group-flush" >
				<li class="list-group-item" > <label> 결제 방법 : </label>
					&nbsp; &nbsp; &nbsp; &nbsp;
					<c:if test="${myOrder.payType == 0 }"> 무통장 입금</c:if>
					<c:if test="${myOrder.payType == 1 }"> 카드 결제 </c:if>
				</li>
				<li class="list-group-item" > <label> 결제 금액 : </label>
				&nbsp; &nbsp; &nbsp; &nbsp; ${myOrder.totalPrice }원 </li>
				<li class="list-group-item"> <label>결제 예정일 : </label>
				&nbsp; &nbsp; &nbsp; &nbsp; ${gItem.paymentDate} </li>
			</ul>
		</div>
	</div>
	<br/>
	<c:set var="targetUrl"><c:url value="/group" /></c:set>
	<form name="bform" action="${targetUrl}" method="post" style="margin : 0px auto;">
		<div class="w3-center interval funcs" style="padding-bottom: 20%;">
		  		<input type="submit" value="공동구매 페이지로 "> &nbsp;&nbsp;&nbsp;
	  	  		<a href="<c:url value='/order/view/3' />"><input type="button" value="주문조회 페이지로 "></a>
	  	</div>
	</form>
</div>
</c:if>
</body>
</html>