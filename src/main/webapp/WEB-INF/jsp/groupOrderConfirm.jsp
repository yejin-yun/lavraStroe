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
</head>
<body>
<%@ include file="header.jsp" %>

<c:if test="${success == true}">
	<p>공동구매 신청에 성공하였습니다. 주문번호 : ${myOrder.orderId}</p>
</c:if>
</body>
</html>