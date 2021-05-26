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
<title>Lavra: group purchase page</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />" >

</head>
<body>
	<%@ include file="header.jsp" %>
	</br>
	
	<c:if test="${gitemList == '' || gitemList eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">아직 상품 준비가 되지 않았습니다.</div>
	</c:if>
	
	<c:set var="listSize" value="${fn:length(gitemList)}" />
	<section>
		<!-- <div style = "width: 100%; height: 50px; margin-top:10px; margin-left:auto; margin-right: auto; background-color : gray;">
			<form name="gItem_sort" method="Get" action = "<c:url value='/group/sorting'/>">
				구분 : 
				<select id="product" name="product">
					<option value="all" selected>전체</option>
					<option value="earring">귀걸이</option>
					<option  value="necklace">목걸이</option>
					<option  value="ring">반지</option>
					<option  value="bracelet">팔찌</option>
					<option  value="hairdeco">머리장식</option>
					<option  value="etcthings">기타</option>
				</select> &nbsp;
				정렬 : 
				<select id="sort" name="sort">
					<option value="basic" selected>기본</option>
					<option  value="popular">인기순</option>
					<option value="deadline">마감일순↑</option>
					<option value="rvdealine">마감일순↓</option>
					<option value="highcost">가격순↑</option>
					<option value="rowcost">가격순↓</option>
				</select> &nbsp;
				
				<input type="submit" value="정렬" />
			</form> 
		</div> -->
		<table>
			<c:if test="${listSize % 4 != 0}">
				<c:set var="fillUpSize" value="${4 - listSize % 4}" />
			</c:if>
			<c:forEach var="groupitem" items="${gitemList}" varStatus="status">
				<c:if test="${status.index % 3 == 0}">
					<tr>
				</c:if>
					<td>
           			<div class="w3-card-4 work">
                        <div class="img_div"> <!-- 이미지를 div의 백으로 넣고, 위에 d-day 표기하는 텍스트 넣기. -->
                           	<img class="main_img" src="<c:url value='${groupitem.item.image}' />" />
                        </div>
                       	<div class="content">
                       		<a href="<c:url value='/group/viewItem'>
            					<c:param name='itemNo' value='${groupitem.item.itemId}' />
            					</c:url>"> 
                            	<h2>${groupitem.item.title}</h2>
                            	<p>${groupitem.item.price}원 &emsp;
                            		진행도 : ${groupitem.percent}%</p>
                            </a>
                        </div>
                    </div>
                  </td>
                
				<c:if test="${status.index % 3 == 2}">
					</tr>
				</c:if>
			</c:forEach>
			<c:choose>
                	<c:when test="${fillUpSize == 1}"><td></td></tr></c:when>
                	<c:when test="${fillUpSize == 2}"><td></td><td></td></tr></c:when>
                	<c:when test="${fillUpSize == 3}"><td></td><td></td><td></td></tr></c:when>
            </c:choose>
		</table>
	</section>
	
</body>
</html>