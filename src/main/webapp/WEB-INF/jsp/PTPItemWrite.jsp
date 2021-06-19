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
<title>Lavra: ptp item insert</title>

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
<c:set var="targetUrl"><c:url value="/이동할 url 입력" /></c:set>
<form:form modelAttribute="ptp" method="post" action = "${targetUrl}" > 
<form:errors cssClass="error" />
		<table class = "table table-striped" style="width : 70%; margin-left : 15%; margin-top : 50px; ">
			<thead>
				<tr>
					<th colspan="2">| 제품 정보</th>
				</tr>
			</thead>
			<tbody>
			   <tr>
	            <td>상품명 :</td>
	            <td>
	              <form:input path="item.title" placeholder="제목을 입력해주세요" />
	              <B><font color="red"><form:errors path="item.title" cssClass="error" /></font></B>
	          </tr>
	          <tr>
	            <td>종류</td>
	            <td>
	              <form:select path="item.productId">
							<option value="21000">귀걸이</option>
							<option value="21001">목걸이</option>
							<option value="21002">반지</option>
							<option value="21003">팔찌</option>
							<option value="21004">머리장식</option>
					</form:select>
	              <B><form:errors path="item.productId"/></B>
	              <font color="red"> <form:errors path="item.productId" /> </font> 
	              </td>
	          </tr>
	          <tr>
				<td>사진</td>
				<td>
					<form:input type="file" path="productPhoto"/>
					<font color="red"> <form:errors path="productPhoto" /> </font> 
				</td>
	          </tr>
			  
			  <tr>
			    <td>설명</td>
			    <td><form:input path="item.description" placeholder="ex : 상품 설명을 입력해주세요" /> 
			    <font color="red"> <form:errors path="item.description" /> </font> 
			    </td>
			  </tr>
			  <tr>
			    <td>가격 </td>
			    <td><form:input path="item.price" type="number"/> 
			    <font color="red"> <form:errors path="item.price" /> </font> 
			    </td>
			  </tr>
			  <tr>
			    <td>배송비</td>
			    <td><form:input path="ptpitem.devP" type="number"/>
			    <font color="red"> <form:errors path="ptpitem.devP" /> </font> 
			     </td>
			  </tr>
			  <tr><td colspan="5"></td></tr>
			  <tr>
			   	<th colspan="2">계좌 정보</th></tr>
			  <tr>
			    <td>은행</td>
			    <td><form:input path="ptpitem.bank" placeholder="ex : 농협 " /> 
			    <font color="red"> <form:errors path="ptpitem.bank" /> </font> 
			    </td>
			  </tr>
			  <tr>
			    <td>계좌번호</td>
			    <td><form:input  path="ptpitem.account" placeholder="ex : 9999 9999 9999 9999" />
			    <font color="red"> <form:errors path="ptpitem.account"/> </font> 
			     </td>
			  </tr>
			  </tbody>
		</table>
		  <br />
    <input name="check" type="image" src="../images/bagic/submit.png"  style = "width:80px; margin-left : 45%;"/>
</form:form>

</body>
</html>