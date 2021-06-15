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

<script>
function showMessage() {
	alert("죄송합니다. 현재 공동구매 페이지는 카드결제만 지원하고 있습니다.");
	document.getElementById('card').checked = true;
	
}
</script>

</head>
<body>
<%@ include file="header.jsp" %>
<br/>
<br/>
<p>
<!--  
받아와야하는 목록 :
신청자 세션 - memer 객체 / 수량, 아이템 정보 <br/>
${gitem.item.itemId} , ${amount} <br/>

입력해야하는 정보 : 
결제 정보 간단히 _ 카드타입, 카드정보, 마감날짜?

배송지 정보: 주소1, 주소2 우편번호 수령인이름

총 결제액 


디자인 심각함. :
-->
</br>
<div style="width: 90%; margin-left : 25%">
<c:set var="targetUrl"><c:url value="/group/newOrderSubmitted.do" /></c:set>
<form:form modelAttribute="myOrder"  action="${targetUrl}" method="post">
 <form:errors cssClass="error" />
<input type="hidden" name="itemId" value="${gitem.item.itemId}">
상품 정보 :
<table border = "0">
	<tr>
		<td rowspan="3">
			<img src="${gitem.item.image}" width="240" height="200">
		</td>
		<td>
			상품명 : ${gitem.item.title}
		</td>
	</tr>
	<tr>
		<td>
			결제 비용 : ${gitem.item.price} * ${myOrder.groupOrder.quantity} = <fmt:formatNumber value="${myOrder.totalPrice}" pattern="###,###,###"/>
		</td>
	</tr>
	<tr>
		<td>
			결제 예정일 : ${gitem.paymentDate}
		</td>
	</tr>
</table>
<br/>
주문자 정보 :
<table border = "0">
	<tr>
		<td > 주문자 성명 </td>
		<td> <form:input path="shipname" readonly="true"/></td>
	</tr>
	<tr>
		<td>우편 번호 </td> 
		<td> <form:input path="shipZip" /> </br> <form:errors path="shipZip" /></td>
	</tr>
	<tr>
		<td>배송지 주소1 </td> 
		<td> <form:input path="shipAddr1"/> </br> <form:errors path="shipAddr1" /> </td>
	</tr>
	<tr>
		<td>배송지 주소2 </td> 
		<td> <form:input path="shipAddr2"/> </br> <form:errors path="shipAddr2" /> </td>
	</tr>
</table>
<br/>
결제 정보 :
<table border = "0">
<tr>
<td> 결제 방법 : </td>
<td>
<form:radiobutton path="payType" value = "0" label="무통장 입금" onclick="showMessage()" />
<form:radiobutton path="payType" id="card" value = "1" label="카드 결제" checked="true" />
</td>
</tr>
<tr>
 	<td colspan = "2">
 		<table border = "0">
 			 <tr>
      			<td>Card Type:</td>
      			<td><form:select path="cardType" items="${creditCardTypes}" /> </td>
    		</tr>
    		<tr>
			     <td>Card Number:</td>
			     <td><form:input path="cardNum" placeholder="ex : 9999 9999 9999 9999 "/> </br> <form:errors path="cardNum" />  </td>
			</tr>
			<tr>
			     <td>Expiry Date : </td>
			     <td><form:input path="expiryDate" placeholder="ex : (MM/YY)"/> </br> <form:errors path="expiryDate" />  </td>
			</tr>
 		</table>
 	</td>
</tr>
</table>
<input type="submit" value="구매 신청하기"/>
</form:form>

</div>


</body>
</html>