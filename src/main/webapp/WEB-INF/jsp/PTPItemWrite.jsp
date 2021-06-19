 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lavra: ptp item insert</title>

<script>

	function backPage() {
		history.back();
	}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
<form:form modelAttribute="ptp" method="post"> <br><br>
		<table border = "1">
			<thead>
				<tr>
					<th colspan="2">제품 정보</th>
				</tr>
			</thead>
			<tbody>
			   <tr>
	            <td>상품명 :</td>
	            <td>
	              <form:input path="item.title" />
	              <B><form:errors path="item.title" cssClass="error" /></B>
	          </tr>
	          <tr>
	            <td>종류</td>
	            <td>
	              <form:select path="item.productId">
							<option value="">종류 선택</option>
							<option value="21000">귀걸이</option>
							<option value="21001">목걸이</option>
							<option value="21002">반지</option>
							<option value="21003">팔찌</option>
							<option value="21004">머리장식</option>
					</form:select>
	              <B><form:errors path="item.productId" cssClass="error" /></B></td>
	          </tr>
	          <tr>
				<td>사진</td>
				<td>
					<form:input type="file" path="productPhoto"/>
				</td>
	          </tr>
			  
			  <tr>
			    <td>설명</td>
			    <td><form:input path="item.description" /> </td>
			  </tr>
			  <tr>
			    <td>가격 </td>
			    <td><form:input path="item.price" /> </td>
			  </tr>
			  <tr>
			    <td>배송비</td>
			    <td><form:input type="number" path="devP" /> </td>
			  </tr>
			  <tr>
			   	<th colspan="2">계좌 정보</th></tr>
			  <tr>
			    <td>은행</td>
			    <td><form:input path="bank" /> </td>
			  </tr>
			  <tr>
			    <td>계좌번호</td>
			    <td><form:input type="number" path="account" /> </td>
			  </tr>
			  </tbody>
		</table>
		  <br />
    <input name="check" type="image" src="../images/bagic/submit.png"  style = "width:80px"/>
</form:form>

</body>
</html>