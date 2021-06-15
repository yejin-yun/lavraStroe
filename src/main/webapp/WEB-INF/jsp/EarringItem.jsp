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
<title>Lavra: Earring</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />">

<script>
	//위시리스트 
	
</script>
<script>
	function moveTarget(targetUri) {
		//alert(targetUri);
		form1.action = targetUri;  //쿼리스트링 사라짐. get은 사라지는데, post는 사라지지 않음. https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=damuri1&logNo=100157143687
		form1.submit();
	}
	
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
			url: "/accessory/wish",
			contentType : "application/json",
			data: jsonStr,
			processData: false,
			success: function(response) {
				if(response === "success") {
					//alert('success');
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
				alert("이미 위시리스트에 담겨 있습니다.", arguments);
			}
		});
	}

</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<!--  널일 때 아래 결과 제대로 나오는지 확인 안해봄 -->
	<!-- 아이템 12개 이상 넣어서 페이지 넘어가는지 체크하기 + 인기순 등등 해보기 -->
	<div id="sortSelect">
		<%-- <form:form modelAttribute="Item">
			<form:select path="sort" >
				<form:options items="${sortData}" itemLabel="label" itemValue="code" />
			</form:select>
		</form:form> --%>
		<c:forEach var="element" items="${sortData}" varStatus="status">
			<a href='<c:url value="/accessory/earring/1?sort=${element}"/>'>
				<font color="black"><B>${element}</B></font>
			</a>
			<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
		</c:forEach>
	</div>
	<c:if test="${itemList == '' || itemList eq null}">
		<div style="width: 50%; margin-left: auto; margin-right: auto;">아직
			상품 준비가 되지 않았습니다.</div>
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
						
						<!-- 해당 user가 wish에 이 상품을 가지고 있는지. 로그인을 안하면 다 0. -->
						<c:if test="${isInWish != 0}">
							<c:set var="like_src" value="/images/bagic/heart-full.png" />
						</c:if>
						<c:if test="${isInWish == 0}">
							<c:set var="like_src" value="/images/bagic/heart-thin.png" />
						</c:if>
						<div class="contain_div" style="position: relative;">
							<div class="img_wish" style="position: absolute; margin-left: 85%; margin-top: 8%;">
									<a href="#" id="like" onClick="changeLikeImg(${item.itemId}, ${isInWish})">					<!--  <c:url value='/accessory/wish'>
										<c:param name="no" value="${item.itemId}" />
										<c:param name="isInWish" value="${isInWish}" /> 
										</c:url> --> <!-- onClick="wishItem(${item.itemId}, ${isInWish})" -->
										<img src="<c:url value='${like_src}' />" id="like_img${item.itemId}" alt="하트(좋아요)" class="heart"/>
									</a>
							</div>
							<div class="img_div">
								<a href="<c:url value='/accessory/detail'>
		            				<c:param name='no' value='${item.itemId}' /></c:url>">
									<img class="main_img" src="<c:url value='${item.image}' />" />
								</a> 	
							</div>
						</div>
						<div class="content">
							<a href="<c:url value='/accessory/detail'>
	            				<c:param name='no' value='${item.itemId}' />
	            				<%-- <c:param name='isLogined' value='${isLogined}' /> --%></c:url>">
								<%-- 컨트롤러에서 로그인 세션으로 검증하기 --%>
								<h3>${item.title}</h3> 
								<p>${item.price}원</p>
							</a>
						</div>
					</div>
				</td>

				<c:if test="${status.index % 4 == 3}">
					</tr>
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${fillUpSize == 1}">
					<td></td>
					</tr>
				</c:when>
				<c:when test="${fillUpSize == 2}">
					<td></td>
					<td></td>
					</tr>
				</c:when>
				<c:when test="${fillUpSize == 3}">
					<td></td>
					<td></td>
					<td></td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		<div id="paging">
			<c:forEach var="val" begin="1" end="${totalPageSize}"
				varStatus="status">
				<a href='<c:url value="/accessory/earring/1?page=${val}&sort=${sort}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
	</section>

	<%-- <a href="<c:url value='/user/wishlistLike'>
	
	<a href="<c:url value='/accessory/earring/detail'>
	            				<c:param name='no' value='${item.itemId}' />
	            				<c:param name='isLogined' value='${isLogined}' /></c:url>">
								<img class="main_img" src="<c:url value='${item.image}' />" /
	                            	<c:if test="${isInWish == 0}" >
	        							<c:param name="like" value="1" />
	        						</c:if>
	        						<c:if test="${isInWish != 0}" >  isInWish에 wishlistId가 들어가서 !=0으로 체크해야 함
	        							<c:param name="like" value="0" />
	        					</c:if>
	        						<c:param name="isLogined" value="1" /> 이걸 쓰려면 controller에서 isLogined를 쓰면 안됨
	        						<c:param name="artworkNo" value="${artworkNo}" />
	        						</c:url>"> --%>
</body>
</html>