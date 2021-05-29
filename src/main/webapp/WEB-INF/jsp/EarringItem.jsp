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
<title>Lavra: Earring</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />" >

 <script>
        //위시리스트 
        $(document).ready(function(){
            $('.heart').click(function() {
                    if( $(this).attr('src') == "/images/bagic/heart-thin.png"){
                        $(this).attr('src','/images/bagic/heart-full.png'); 
                    }else {
                        $(this).attr('src','/images/bagic/heart-thin.png'); 
                    }
            });
        });
    </script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<!--  널일 때 아래 결과 제대로 나오는지 확인 안해봄 -->
	<!-- 아이템 12개 이상 넣어서 페이지 넘어가는지 체크하기 + 인기순 등등 해보기 -->
	<c:if test="${itemList == '' || itemList eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">아직 상품 준비가 되지 않았습니다.</div>
	</c:if>
	<c:set var="listSize" value="${fn:length(itemList)}" />
	<section>
		<table>
			<c:if test="${listSize % 4 != 0}">
				<c:set var="fillUpSize" value="${4 - listSize % 4}" />
			</c:if>
			<c:forEach var="item" items="${itemList}" varStatus="status">
				<c:if test="${status.index % 4 == 0}">
					<tr>
				</c:if>
					<td>
           			<div class="w3-card-4 work">
           				<c:set var="isInWish" value="${item.isInWishlist}" />  
                        <c:set var="isInCart" value="${item.isInCart}" />
	                        <div class="img_div">
	                        	<a href="<c:url value='/accessory/earring/detail'>
	            				<c:param name='no' value='${item.itemId}' />
	            				<%-- <c:param name='isLogined' value='${isLogined}' /> --%></c:url>">
	                            <img class="main_img" src="<c:url value='${item.image}' />" /></a>
	                        </div>
                        	<div class="content">
                        		<a href="<c:url value='/artwork/detail'>
	            				<c:param name='artworkNo' value='${artworkNo}' />
	            				<%-- <c:param name='isLogined' value='${isLogined}' /> --%></c:url>"> <%-- 컨트롤러에서 로그인 세션으로 검증하기 --%>
	                            <h2>${item.title}</h2></a>
	                            <c:if test="${isInWish == 1}" >
	        						<c:set var="like_src" value="/images/bagic/heart-full.png" />
	        					</c:if>
	        					<c:if test="${isInWish == 0}" >
	        						<c:set var="like_src" value="/images/bagic/heart-thin.png" />
	        					</c:if>
	                            <a href="<c:url value='/user/wishlistLike'>
	                            	<c:if test="${isInWish == 0}" >
	        							<c:param name="like" value="1" />
	        						</c:if>
	        						<c:if test="${isInWish == 1}" >
	        							<c:param name="like" value="0" />
	        						</c:if>
	        						<c:param name="isLogined" value="1" /> <%-- 이걸 쓰려면 controller에서 isLogined를 쓰면 안됨 --%>
	        						<c:param name="artworkNo" value="${artworkNo}" />
	        						</c:url>">
	                            	<img src="<c:url value='${like_src}' />" id="like_img"
	                                	alt="하트(좋아요)" class="heart"
	                                	style="padding-bottom: 10px; float: right; padding-right: 10px;"/>
	                            </a>
	                            <p>${item.price}원</p>
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
			<c:forEach var="val" begin="1" end="${totalPageSize}" varStatus="status">
				<a href='<c:url value="/accessory/earring/1?page=${val}"/>'>
            	<font color="black"><B>${val}</B></font></a>
            	<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach> 
        </div>
	</section>


</body>
</html>