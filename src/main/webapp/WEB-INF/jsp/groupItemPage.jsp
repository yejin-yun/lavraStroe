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
<title>${gitem.item.title} 페이지</title>
</head>
<body>
아이템 상세페이지
<c:if test="${gitem == '' || gitem eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">상품 로딩 중 문제가 발생하였습니다.</div>
</c:if>
<br/>
${gitem.item.title} 페이지
</body>
</html>