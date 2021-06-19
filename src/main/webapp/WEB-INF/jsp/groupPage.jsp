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
<script type="text/javascript">
function changeLikeImg(itemId, wish) {
	 //$('#like_img'+itemId).click(function() {
        if( $('#like_img'+itemId).attr('src') == "/images/bagic/heart-thin.png"){
            $('#like_img'+itemId).attr('src','/images/bagic/heart-full.png'); 
        }else {
            $('#like_img'+itemId).attr('src','/images/bagic/heart-thin.png'); 
        }
	//});
	 
        wishItem(itemId, wish);
}

function wishItem(itemId, wish){
	//var msg = itemId; //itemId 하나만 json으로 보낼때. 아래 방식은 객체라서  int로 변환 못시킴
	
	var msg = {
		"itemId":itemId,
		"isInWishlist":wish
	};
	var jsonStr = JSON.stringify(msg);

	$.ajax({
		type: "POST",
		url: "/group/wish",
		contentType : "application/json",
		data: jsonStr,
		processData: false,
		success: function(response) {
			if(response === "success") {
				//alert(wish);
			}
			else if(response === "LoginForm") {
				var allow = confirm('로그인을 먼저 해주세요');
				if(allow){
					location.href="/shop/loginForm.do";
				}
			}
			else {
				location.href="/error";
			}
			
		},
		error: function(){
			//alert(wish);
			alert("이미 위시리스트에 담겨 있습니다.", arguments);
		}
	});
}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	</br>
	<div id="sortSelect" style="margin-right : 10%;">
		<c:forEach var="element" items="${sortData}" varStatus="status">
			<a href='<c:url value="/group?sort=${element}"/>'>
				<font color="black"><B>${element}</B></font>
			</a>
			<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
		</c:forEach>
	</div>
	<c:if test="${gitemList == '' || gitemList eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">아직 상품 준비가 되지 않았습니다.</div>
	</c:if>
	
	<c:set var="listSize" value="${fn:length(gitemList)}" />
	<section>
		<table>
			<c:if test="${listSize % 4 != 0}">
				<c:set var="fillUpSize" value="${4 - listSize % 4}" />
			</c:if>
			<c:forEach var="groupitem" items="${gitemList}" varStatus="status">
				<c:if test="${status.index % 4 == 0}">
					<tr>
				</c:if>
					<td>
           			<div class="w3-card-4 work">
           				<c:set var="isInWish" value="${groupitem.item.isInWishlist}" />
						
						<!-- 해당 user가 wish에 이 상품을 가지고 있는지. 로그인을 안하면 다 0. -->
						<c:if test="${isInWish != 0}">
							<c:set var="like_src" value="/images/bagic/heart-full.png" />
						</c:if>
						<c:if test="${isInWish == 0}">
							<c:set var="like_src" value="/images/bagic/heart-thin.png" />
						</c:if>
						
						<div class="contain_div" style="position: relative;">
							<div class="img_wish" style="position: absolute; margin-left: 85%; margin-top: 8%;">
									<a href="javascript:;" id="like" onClick="changeLikeImg(${groupitem.item.itemId}, ${isInWish})">			
										<img src="<c:url value='${like_src}' />" id="like_img${groupitem.item.itemId}" alt="하트(좋아요)" class="heart"/>
									</a>
							</div>
                        	<div class="img_div"> <!-- 이미지를 div의 백으로 넣고, 위에 d-day 표기하는 텍스트 넣기. -->
                           		<a href="<c:url value='/group/viewItem'>
            								<c:param name='itemNo' value='${groupitem.item.itemId}' /> 
            							</c:url>"> 
                            		<img class="main_img" src="<c:url value='${groupitem.item.image}' />" />
                           		</a>	
                        	</div>
                        </div>
                       	<div class="content">
                       		<a href="<c:url value='/group/viewItem'>
            					<c:param name='itemNo' value='${groupitem.item.itemId}' />
            					</c:url>"> 
                            	<h2>${groupitem.item.title}</h2>
                            	<p>${groupitem.item.price}원 &emsp;
                            		진행도 : ${groupitem.percent}%
                            		<c:if test="${groupitem.status == 0}"> <font> 진행중 </font> </c:if>	
                            		<c:if test="${groupitem.status != 0}"> <font color='red'> 마감</font> </c:if>	
                            	</p>
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
				<a href='<c:url value="/group?page=${val}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
	</section>
	
</body>
</html>