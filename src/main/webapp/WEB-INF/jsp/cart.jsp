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
	<title>Lavra</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
		table {
		   
		}
	</style>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<c:if test="${cartItemList == '' || cartItemList eq null}">
		<div style="width: 50%; margin-left: auto; margin-right: auto;">
		아직 상품 준비가 되지 않았습니다.</div>
	</c:if>
	<form method="POST"> <%-- action이 없으면 얘를 부른 컨트롤러로 넘어간다. 체크한 것만 넘어가면 돼서 Command 객체 필요 없음. form:form을 사용안 한 건 여기서 하나만 선택한 결과를 알고 싶은 게 아니고, 어떤 것들이 선택 되었는지가 중요하기 때문. 즉 path를 설정할  게 없음--%>
		<div class="container" style="margin-top: 10%;">
		<table class="table table-hover">
	    <thead>
	      <tr>
	        <th>선택</th>
	        <th>상품명</th>
	        <th></th>
	        <th>가격</th>
	        <th>수량</th>
	        <th>합계</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:set var="allTotalCost" value="0" />
	    	<c:forEach var="cartItem" items="${cartItemList}">
	    		<tr>
	    			<c:set var="item" value="${cartItem.item}" />
	    			<c:set var="itemTotalCost" value="${item.price * cartItem.quantity}" />
	    			<td><input type="checkbox" name="checkCartItem" value="${cartItem.cartItemId}" id="${cartItem.cartItemId}" class="checkWish allCheckbox"/> </td>
	    			<td><img style="height: 50px;" src="<c:url value='${item.image}' />" /></td>
	    			<td>${item.title}</td>
	    			<td>${item.price}</td>
	    			<td>${cartItem.quantity}</td>
	    			<td>${itemTotalCost}</td>
	    			<c:set var="allTotalCost" value="${allTotalCost + itemTotalCost}" />
	    		</tr>
	    	</c:forEach>
	    </tbody>
	    </table>
    </div>
	</form>
</body>
</html>