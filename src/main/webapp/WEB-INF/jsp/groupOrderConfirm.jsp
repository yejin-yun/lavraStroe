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
<script>
	function goGroup(targetURL) {
		location.href=targetURL;
	}

</script>
</head>
<body>
<%@ include file="header.jsp" %>

<br>
<br>

<c:if test="${success == true}">

<div>
	<h2> 
		<span>${myOrder.memberId}님</span>
		<span> 항상 Lavra를 이용해주셔서 감사합니다 </span>
	</h2>
	<p>
		<span> "[주문번호 : <strong>${myOrder.orderId}</strong>]가 신청되었습니다.</span> <!-- 마이페이지 구현 시 주문번호에 링크걸기. -->
	</p>
	<div id=shipInfo>
		<h3>배송 정보 :</h3>
		<div>
			<ul>
				<li> 받는분 : ${myOrder.shipname}</li>
				<li> 주소 : ${myOrder.shipAddr1 } ${myOrder.shipAddr2 }</li> 
				<li> 배송 예정일 : 공동구매의 경우, 목표 금액 달성 시 결제 예정일날 결제되어 이후 2~3일 내로 출고됩니다.</li> 
			</ul>
		</div>
	</div>
	
	<div id="itemInfo">
		<h3> 상품 정보 : </h3>
		<table>
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
	
	<div id=payInfo>
		<h3>결제 정보 : </h3>
		<div>
			<ul>
				<li> 결제 수단 : 
					<c:if test="${myOrder.payType == 0 }"> 무통장 입금</c:if>
					<c:if test="${myOrder.payType == 1 }"> 카드 결제 </c:if>
				</li>
				<li> 결제 금액 : ${myOrder.totalPrice }원 </li>
				<li> 결제 예정일 : ${gItem.paymentDate} </li>
			</ul>
		</div>
	</div>
	
	<a href="<c:url value='/group'/>">
		<button type="button"> 공동구매 페이지로 </button>
	</a>
</div>
</c:if>
</body>
</html>