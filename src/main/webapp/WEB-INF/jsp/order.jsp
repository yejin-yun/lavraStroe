 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>주문 조회 페이지</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />"> <%-- #paging 때문에 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<style>
 
	th, td {
		text-align : center;
	}
	
	td:nth-child(1) {
		width : 60px;
	}
	td:nth-child(2) {
		width : 80px;
	}
	td:nth-child(3) {
		width : 130px;
	}
	td:nth-child(4) {
		width : 80px;
	}
	td:nth-child(5) {
		width : 100px;
	}
	td:nth-child(6) {
		width : 60px;
	}
	td:nth-child(7) {
		width : 60px;
	}
	td:nth-child(8) { 
		width : 100px;
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
		
		.order_info {
			float:right; 
			position:absolute; 
			left: 70%;
			right: 5%;
		}
		
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container"  style="margin-left: auto; margin-right: auto; width: 90%; margin-top:5%;">
	<ul class="nav nav-tabs">
		<c:if test="${view eq 1}">
	  		<li class="active"><a href="/order/view/1">악세사리</a></li>
	  		<li><a href="/order/view/2">개인거래</a></li>
	  		<li><a href="/order/view/3">공동구매</a></li>
	  	</c:if>
	  	<c:if test="${view eq 2}">
	  		<li ><a href="/order/view/1">악세사리</a></li>
	  		<li class="active"><a href="/order/view/2">개인거래</a></li>
	  		<li><a href="/order/view/3">공동구매</a></li>
	  	</c:if>
	  	<c:if test="${view eq 3}">
	  		<li><a href="/order/view/1">악세사리</a></li>
	  		<li><a href="/order/view/2">개인거래</a></li>
	  		<li class="active"><a href="/order/view/3">공동구매</a></li>
	  	</c:if>
	</ul>
</div> 
<div class="container" style="margin-top: 5%; width : 90%;">
	<table class="table table-striped">
	    <thead>
	      <tr>
	        <th>주문번호</th>
	        <th>주문일자</th>
	        <th>주문내역</th>
	        <th>주문금액</th>
	        <th>주문상태</th>
	        <th>주문자</th>
	        <th>수령자</th>
	        <th>배송지</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<!-- 카테고리 id랑 햇갈릴까봐, 적어둡니다. ${view} =1일 경우 쇼핑몰, 2일 경우 개인 거래, 3일 경우 공동구매입니다.-->
	    	<c:forEach var="order" items="${orderList}">
	    		<tr>
	    			<td> ${order.orderId}</td>
	    			<td> ${order.orderDate}</td>
	    			<td> 
	    				<!-- 주문 내역. 각자, 자신의 주문 상태에 맞게 아이템 표시해주세요. (링크 걸기는 자유) -->
	    				<c:if test="${view == 1 }"> 
	    					<c:set var="listSize" value="${fn:length(order.lineItems)}" />
	    					<a href="<c:url value='/item/viewOrder'>
	            						<c:param name='no' value='${order.orderId}' /> 
	            					</c:url>"> 
	            					<c:if test="${listSize > 1}" >
	                            		<font color="blue"> ${order.lineItems[0].item.title} 외</font>
	                            	</c:if>
	                            	<c:if test="${listSize == 1}" >
	                            		<font color="blue"> ${order.lineItems[0].item.title}</font>
	                            	</c:if>
	                         </a>
	    				</c:if>
	    				<c:if test="${view == 2 }"> </c:if>
	    				<c:if test="${view == 3 }"> 
		    				<a href="<c:url value='/group/viewItem'>
	            						<c:param name='itemNo' value='${groupitem.item.itemId}' /> 
	            					</c:url>"> 
	                            	<font color="blue"> ${order.groupOrder.groupItem.item.title } </font>
	                         </a>
	    				</c:if>
	    			
	    			</td>
	    			<td> ${order.totalPrice }</td>
	    			<td> 
	    				<c:if test="${view != 3 }"> 주문 완료 </c:if>
	    				<c:if test="${view == 3 }">
	    					<c:if test = "${order.groupOrder.status eq 0}"> 공동 구매 신청 </c:if>
	    					<c:if test = "${order.groupOrder.status eq 1}"> 주문 완료 </c:if>
	    					<c:if test = "${order.groupOrder.status eq 2}"> 공동 구매 실패 </c:if>
	    				</c:if>
	    			</td>
	    			<td> ${member.username}</td>
	    			<td> ${order.shipname}</td>
	    			<td> ${order.shipAddr1} ${order.shipAddr2}</td>
	    		</tr>
	    		<tr>
	    			<td colspan="8">
	    				<div style="text-align : center;">
	    					<c:if test="${order.payType eq 0}"> 결제방법 : 무통장 입금  &nbsp;&nbsp;&nbsp; ${order.orderDate }일 정상적으로 입금 확인되셨습니다.</c:if>
	    					<c:if test="${order.payType eq 1}"> 결제방법 : 카드결제 &nbsp;&nbsp;&nbsp; 결제 카드 : ${order.cardType} 카드번호 : ${order.cardNum }</c:if>
	    				</div>
	    			</td>	
	    		</tr>
	    		
	    	</c:forEach>
	    </tbody>
	</table>

</div>
</body>
</html>