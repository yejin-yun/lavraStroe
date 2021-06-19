<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lavra: Sell Item List</title>

<script>
	$(document).ready(function() {
		$("#btnAdd").click(function() {
			location.href="${path}/sellList/view/write.do"
		});
	});
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<br>
	<button type="button" id="btnAdd">상품 등록</button><br>
	
	<div class="container"  style="margin-left: auto; margin-right: auto; width: 90%; margin-top:5%;">
		<ul class="nav nav-tabs">
			<c:if test="${view eq 1}">
	  			<li class="active"><a href="/sellList/view/0">진열중</a></li>
	  			<li><a href="/sellList/view/1">구매 요청</a></li>
	  			<li><a href="/SellList/view/2">판매 완료</a></li>
	  		</c:if>
	 	 	<c:if test="${view eq 2}">
	 	 		<li ><a href="/sellList/view/0">진열중</a></li>
	 	 		<li class="active"><a href="/sellList/view/1">구매 요청</a></li>
	 	 		<li><a href="/sellList/view/2">판매 완료</a></li>
		  	</c:if>
		  	<c:if test="${view eq 3}">
		  		<li><a href="/sellList/view/0">진열중</a></li>
	 	 		<li><a href="/sellList/view/1">구매 요청</a></li>
	 	 		<li class="active"><a href="/sellList/view/2">판매 완료</a></li>
		  	</c:if>
		</ul>
	</div>
	<form method="POST" name="form">
		<div class="container" style="margin-top: 5%";">
			<table>
				<thread>
					<tr>
						<th>상품명</th>
						<th>설명</th>
						<th>가격</th>
						<th>배송비</th>
						<th>정보</th>
					</tr>
				</thread>
			</table>
		</div>
	</form> 
	
</body>
</html>