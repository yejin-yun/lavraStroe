<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Lavra: ptp item update</title>

<script>
	function backPage() {
		history.back();
	}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	
	<c:set var="targetUrl"><c:url value="/shop/productupdate.do" /></c:set>
	<p style=" width : 100%; text-align : center;"> <b> [!!] 상품명과 사진은 수정하실 수 없습니다. </b></p>
	<form:form modelAttribute="pitem" method="post" action = "${targetUrl}" style="width : 80%; margin-left : 20%;"> 
	<form:errors cssClass="error" />
		<input type="hidden" name="itemId" value="${pitem.item.itemId}">
		<table class = "table table-striped" style="width : 65%;">
			<thead>
				<tr>
					<th colspan="2">| 제품 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<td>상품명 :</td>
					<td>
						${pitem.item.title}
					</td>
				</tr>
				<tr>
					<td>사진</td>
					<td>
						${pitem.item.image}
					</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>
						<form:input path="item.description" value="${ptiem.item.description}" placeholder="ex : 상품 설명을 입력해주세요" /> 
			    		<font color="red"> <form:errors path="item.description" /> </font> 
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<form:input path="item.price" value="${pitem.item.price}" type="number" placeholder="ex : 상품 가격을 입력해주세요."/> 
			  			<font color="red"> <form:errors path="item.price" /> </font> 
					</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td>
						<form:input path="devP" value="${ptiem.devP}" placeholder="ex : 배송비를 입력해주세요" /> 
			    		<font color="red"> <form:errors path="devP" /> </font> 
					</td>
				</tr>
				<tr>
					<th colspan="2">계좌 정보</th>
				</tr>
				<tr>
					<td>은행</td>
					<td>
						<form:input path="bank" value="${ptiem.bank}" placeholder="ex : 은행을 입력해주세요" /> 
			    		<font color="red"> <form:errors path="bank" /> </font> 
					</td>
				</tr>
				<tr>
					<td>계좌번호</td>
					<td>
						<form:input path="account" value="${ptiem.account}" placeholder="ex : 계좌를 입력해주세요" /> 
			    		<font color="red"> <form:errors path="account" /> </font> 
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<input name="check" type="image" src="../images/bagic/submit.png"  style = "width:80px; margin-left : 30%;"/>
	</form:form>
</body>
</html>