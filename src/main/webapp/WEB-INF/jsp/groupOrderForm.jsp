<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공동 구매 신청 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
function showMessage() {
	alert("죄송합니다. 현재 공동구매 페이지는 카드결제만 지원하고 있습니다.");
	document.getElementById('card').checked = true;
	
}
function backPage() {
	history.back();
}
</script>
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
<br/>
<br/>
<p>

</br>
<div class="container">
<c:set var="targetUrl"><c:url value="/group/newOrderSubmitted.do" /></c:set>
<form:form modelAttribute="myOrder"  action="${targetUrl}" method="post">
 	<form:errors cssClass="error" />
	<input type="hidden" name="itemId" value="${gitem.item.itemId}">
	<div id = "itemInfo">
		<table class="table table-striped">
    		<thead>
     			 <tr>
        			<th colspan = "3">| 상품정보</th>
       			</tr>
    		</thead>
    		<tbody>
    			<tr>
       				<td rowspan = "4" style="width : 30%;" > <img src="${gitem.item.image}" width="300" height="250"> </td>
      			</tr>
      			<tr>
        			<td style="width : 15%;" > <h5> 상품명 : </h5> </td>
        			<td> ${gitem.item.title} </td>
				</tr>
				<tr>
					<td style="width : 15%;" > <h5>  결제 비용 : </h5> </td>
					<td> ${gitem.item.price} * ${myOrder.groupOrder.quantity} = <fmt:formatNumber value="${myOrder.totalPrice}" pattern="###,###,###"/> 원 </td>
				</tr>
				<tr>
					<td style="width : 15%;" > <h5>  결제 예정일 : </h5> </td> 
					<td>${gitem.paymentDate} </td>
				</tr>
   			 </tbody>
  		</table>
	</div>
<br/>
	<div>
		<table class="table table-striped">
		  		<thead>
		   			 <tr>
		      			<th colspan = "3">| 주문자 정보</th>
		     			</tr>
		  		</thead>
		  		<tbody>	
		  			<tr>
					<td > <label> 주문자 성명 </label></td>
					<td><form:input path="shipname" readonly="true"/></td>
				</tr>
				<tr>
					<td ><label>우편 번호 </label></td> 
					<td><form:input path="shipZip"/> <font color="red"> <form:errors path="shipZip" /> </font>  </td>
				</tr>
				<tr>
					<td  ><label> 배송지 주소1 </label> </td> 
					<td><form:input path="shipAddr1" /> <font color="red"> <form:errors path="shipAddr1" /> </font> </td>
				</tr>
				<tr>
					<td  ><label> 배송지 주소2 </label> </td> 
					<td ><form:input path="shipAddr2"/> <font color="red"> <form:errors path="shipAddr2" /> </font>  </td>
				</tr>
			</tbody>
		</table>
	</div>
	<br/>
	<div>
		<table class="table table-striped">
	  		<thead>
	   			 <tr>
	      			<th colspan = "3">| 결제 정보 </th>
	     			</tr>
	  		</thead>
	  		<tbody>	
	  			<tr>
					<td > <label> 결제 방법 </label> </td>
					<td>
						<form:radiobutton path="payType" value = "0" label="무통장 입금" onclick="showMessage()" />
						<form:radiobutton path="payType" id="card" value = "1" label="카드 결제" checked="true" />
					</td>
				</tr>
				<tr>
					<td style="width : 32%;" ><label> Card Type</label> </td> 
					<td><form:select path="cardType" items="${creditCardTypes}" /> </td>
				</tr>
				<tr>
					<td style="width : 32%;" > <label>Card Number </label> </td> 
					<td><form:input path="cardNum" placeholder="ex : 9999 9999 9999 9999 "/> <font color="red"> <form:errors path="cardNum" /> </font> </td>
				</tr>
				<tr>
					<td style="width : 32%;"> <label> Expiry Date </label> </td> 
					<td><form:input path="expiryDate" placeholder="ex : (MM/YY)"/>  <font color="red"> <form:errors path="expiryDate" /> </font> </td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="w3-center interval funcs" style="padding-bottom: 20%;">
		  <input type="button" value="취소" style="padding-right: 10px;" onClick="backPage()")>
		  <input type="submit" value="구매 신청하기"/>
	 </div>
</form:form>
</div>


</body>
</html>