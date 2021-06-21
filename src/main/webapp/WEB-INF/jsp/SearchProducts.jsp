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
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />"> <%-- #paging 때문에 --%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	
	<style>
.funcs {
			margin-top: 3%;
			margin-bottom: 10%;
		}
		.funcs input 
		{
			margin-right: 10px;
			padding: 5px;
			background-color: white;
			border: 1px solid #646EFF;
			color: #646EFF;
			border-top-left-radius: 5px; 
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px; 
        	border-bottom-right-radius: 5px;
      }
		.funcs input:hover
		{ 	
			color:white; 
			background-color: #646EFF; 
		}
	</style>

</head>
<body>
<%@ include file="header.jsp"%>

	<c:if test="${ fn:length(itemList.pageList) == 0}">
			<br/><br/>
	    	<h3 style = " margin-top :7%; text-align: center;">
	    	<strong>검색 결과가 없습니다.</strong>
	    	</h3>
	</c:if>
 	<div class="container" style="margin-top: 5%;">
 	<c:if test="${ fn:length(itemList.pageList) != 0}">
		<table > <!-- class="table table-hover" -->
	    <thead>
	      <tr>
	        <th>상품명</th>
	        <th></th>
	        <th>설명</th>
	        <th>가격</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="item" items="${itemList.pageList}">
	    		<tr>
	    			<c:if test="${item.itemId >= 1000 && item.itemId < 10000}">
	    			<td><a href="<c:url value='/accessory/detail'>
		            				<c:param name='no' value='${item.itemId}' /></c:url>">
		            	${item.title}</a></td>
		            </c:if>
		            <c:if test="${item.itemId >= 10000 && item.itemId < 20000}">
	    			<td><a href="<c:url value='/group/viewItem'>
		            				<c:param name='itemNo' value='${item.itemId}' /></c:url>">
		            	${item.title}</a></td>
		            </c:if>
		            <c:if test="${item.itemId >= 20000 && item.itemId < 30000}">
	    			<td><a href="<c:url value='/accessory/viewPItem'>
									<c:param name='itemId' value='${ptpitem.item.itemId}' />
									<c:param name='sellerId' value='${ptpitem.sellerId}' /></c:url>">
		            	${item.title}</a></td> <!-- 개인거래 -->
		            </c:if>
	    			<td><img style="height: 50px;" src="<c:url value='${item.image}' />" /></td>
	    			<td>${item.description}</td>
	    			<td>
	    			<c:choose>
						<c:when test="${item.isSoldout == 0}">
							<fmt:formatNumber value="${item.price}" pattern="###,###,###"/>원
						</c:when>
						<c:when test="${item.isSoldout == 1}">
							<span>품절</span>
						</c:when>
					</c:choose>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    	
	    </tbody>
	    </table>
	    </c:if>
	    <div id="paging">
			<c:forEach var="val" begin="1" end="${totalPageSize}"
				varStatus="status">
				<a href='<c:url value="/cart/view/${categoryId}?page=${val}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
	</div>

</body>
</html>