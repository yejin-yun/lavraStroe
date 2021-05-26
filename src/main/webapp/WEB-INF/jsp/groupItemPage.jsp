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
<%@ include file="header.jsp" %>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:if test="${gitem == '' || gitem eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">상품 로딩 중 문제가 발생하였습니다.</div>
</c:if>
<div align="center" style="width : 90%;">
	<table border="1">
		<tr>
			<td>
				<img src="${gitem.item.image}" width="340" height="300">
			</td>
			<td>
				<table border="1" style="height: 300px; width: 400px;">
					<tr align="center">
						<td>상품명</td>
						<td>${gitem.item.title}</td>
					</tr>
					<tr align="center">
						<td>개당 가격</td>
						<td><fmt:formatNumber value="${gitem.item.price}" pattern="###,###,###"/></td>
					</tr>
					<tr align="center">
						<td>상품소개</td>
						<td>${gitem.item.description}</td>
					</tr>
					
					<tr align="center">
						<td> 결제일(마감일) </td>
						<td> ${gitem.paymentDate}</td>
					</tr>
					<tr align="center">
						<td> 현재 달성율 </td>
						<td> ${gitem.percent}</td>
					</tr>
					
					<tr align="center">
						<td> 진행 상태 </td>
						<c:choose>
        					<c:when test="${gitem.status == 0}">
           						 <td>진행 중</td>
       						</c:when>
       						<c:when test="${gitem.status == 1}">
           						 <td> 마감 성공 (100% 달성)</td>
       						</c:when>         
        					<c:otherwise>
          						 <td> 마감 실패 (100% 미달성) </td>
         					</c:otherwise>
    					</c:choose>
					</tr>
					
					<tr align="center">
						<td colspan="2">
							<form name="form1" method="post" action="">
								<input type="hidden" name="itemId" value="${gitem.item.itemId}">
								<select name="amount">
									<c:forEach begin="1" end="10" var="i">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>&nbsp;개
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${gitem.status == 0}"> <!-- 진행 중인 공구만 신청가능 -->
									<input type="submit" value="구매 신청하기">
								</c:if>
								<c:if test="${gitem.status != 0}">
									<input type="submit" value="구매 신청하기" disabled>
								</c:if>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>