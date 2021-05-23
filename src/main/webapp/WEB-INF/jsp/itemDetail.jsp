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
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <%@ include file="header.jsp" %>
  <h2>상품 상세정보</h2>
  <table border="1">
  	<tr>
  		<td>
  			<img src ="/images/bagic/heart-full.png" width="340" height="300">
  		</td>
  		<td>
  			<table border="1" style="heght : 300px; width: 400px;">
  				<tr align="center">
  					<td>상품명</td>
  					<td>상품 이름 가져와서 넣기</td>
  				</tr>
  				<tr align="center">
  					<td>가격</td>
  					<td><fmt:formatNumber value="2011" pattern="###,###,###"/>value에 가격을 가져와서 넣기</td>
  				</tr>
  				<tr align="center">
  					<td colspan = "2">
						<form name ="cart" method="post" action = "장바구니 추가">
							<input type = "hidden" name="productId" value="프로덕트 아이디 가져와서 넣어주세요">
							<select name = "amount">
								<c:forEach begin="1" end="10" var="i">
								 <option value="${i}">${i} </option>
								 </c:forEach>
							</select>
						</form>
					</td>
  				</tr>
  				
  			</table>
  		</td>
  	</tr>
  </table>
</body>
</html>