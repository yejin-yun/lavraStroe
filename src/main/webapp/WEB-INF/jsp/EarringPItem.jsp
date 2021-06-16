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
<title>Lavra: ptp Earring</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div id="sortSelect">
		<c:forEach var="element" items="${sortData}" varStatus="status">
			<a href='<c:url value="/accessory/${productName}/2?sort=${element}"/>'>
				<font color="black"><B>${element}</B></font>
			</a>
			<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
		</c:forEach>
	</div>
	<c:if test="${pitemList == '' || pitemList eq null}">
		<div style="width: 50%; margin-left: auto; margin-right: auto;">
		등록된 상품이 존재하지 않습니다.</div>
	</c:if>
	<c:set var="listSize" value="${fn:length(pitemList)}" />
	<section>
		<table>
			<c:if test="${listSize % 4 != 0}">
				<c:set var="fillUpSize" value="${4 - listSize % 4}" />
			</c:if>
			<c:forEach var="ptpitem" items="${pitemList}" varStatus="status">
				<c:if test="${status.index % 4 == 0}">
					<tr>
				</c:if>
				<td>
					<div class="w3-card-4 work">
						<div class="img_div">						
							<a href="<c:url value='/accessory/viewItem'>
								<c:param name='itemNo' value='${ptpitem.item.itemId}' />
								</c:url>">
								<img class="main_img" src="<c:url value='${ptpitem.item.image}' />" />
							</a>
						</div>
						<div class="content">
							<a href="<c:url value='/accessory/viewItem'>
								<c:param name='itemNo' value='${ptpitem.item.itemId}' />
								</c:url>">
								<h3>${ptpitem.item.title}</h3>
								<p><fmt:formatNumber value="${ptpitem.item.price}" pattern="###,###,###"/>원</p>
							</a>
						</div>
					</div>
				</td>
				<c:if test="${status.index % 4 == 3}">
					</tr>
				</c:if>
			</c:forEach>
			<c:choose>
                	<c:when test="${fillUpSize == 1}"><td></td></tr></c:when>
                	<c:when test="${fillUpSize == 2}"><td></td><td></td></tr></c:when>
                	<c:when test="${fillUpSize == 3}"><td></td><td></td><td></td></tr></c:when>
            </c:choose>
		</table>
		<div id="paging">
			<c:forEach var="val" begin="1" end="${totalPageSize}"
				varStatus="status">
				<a href='<c:url value="/accessory/${productName}/2?page=${val}&sort=${sort}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
	</section>
</body>
</html>