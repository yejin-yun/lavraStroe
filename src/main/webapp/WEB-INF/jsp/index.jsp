<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<title>Lavra</title>
<meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />">

  <script src="<c:url value='/js/item.js' />" ></script>
  <script>
  	$(document).ready(function() {
  	// Activate Carousel
  		$("#myCarousel").carousel();
  		$("#myCarousel").carousel({interval: 100});

  		// Enable Carousel Indicators
  		$(".item").click(function(){
  		  $("#myCarousel").carousel(1);
  		});

  		// Enable Carousel Controls
  		$(".left").click(function(){
  		  $("#myCarousel").carousel("prev");
  		});
  	});
  </script>
  
  <style>
  	html {
  		width: 100%;
  		height: 100%;
  	}
	.carousel-inner > .item > img,
	.carousel-inner > .item > a > img {
	  width: 70%;
	  margin: auto;
	}
  	#img_slide_container {
  		margin-top: 5%;
  		height: 100%;
  		width: 100%;
  	}
  	.slideImg {
  		 width: auto; height: auto;
    	max-width: 400px;
    	max-height: 500px;
  		object-fit: fill; 
  	}
  </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container" id="img_slide_container">  <%--https://www.w3schools.com/bootstrap/bootstrap_carousel.asp --%>
	<div id="myCarousel" class="carousel slide" data-ride="carousel" style=" height: 80%;">
		<ol class="carousel-indicators" > <%-- 점 부분 --%>
			<c:forEach var="item" items="${itemList}" varStatus="status">
				<c:if test="${status.first}">
					<li data-target="#myCarousel" data-slide-to="${status.index}" class="active"></li>				
				</c:if>
				<c:if test="${!status.first}">
					<li data-target="#myCarousel" data-slide-to="${status.index}"></li>
				</c:if>
			</c:forEach>
		</ol>

	
		<div class="carousel-inner" style="width: 100%; height: 100%;">
			<c:forEach var="item" items="${itemList}" varStatus="status">
				<c:if test="${status.first}">
					<div class="item active">
						<a href="<c:url value='/accessory/detail'>
		            				<c:param name='no' value='${item.itemId}' /></c:url>">
						<img src='<c:url value="${item.image}" />' alt="${item.title}" class="slideImg">
						</a>
	    			</div>	
				</c:if>
				<c:if test="${!status.first}">
					<div class="item">
						<a href="<c:url value='/accessory/detail'>
		            				<c:param name='no' value='${item.itemId}' /></c:url>">
						<img src='<c:url value="${item.image}" />' alt="${item.title}"  class="slideImg"></a>
	    			</div>	
				</c:if>		
			</c:forEach>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
	    	<span class="glyphicon glyphicon-chevron-left"></span>
	    	<span class="sr-only">Previous</span>
	  	</a>
	  	<a class="right carousel-control" href="#myCarousel" data-slide="next">
	   		<span class="glyphicon glyphicon-chevron-right"></span>
	    	<span class="sr-only">Next</span>
	 	</a>
	</div>
</div>

<div style="padding-bottom: 5%; text-align:center;">
	<img src='<c:url value="/images/bagic/bestItem.png" />' width="20%" height="20%">
</div>

<div style="padding-bottom: 5%;">
		<table>
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
							<%-- <div class="img_wish" style="position: absolute; margin-left: 85%; margin-top: 8%;">
									<a href="javascript:;" id="like" onClick="changeLikeImg(${item.itemId}, ${isInWish})">					<!--  <c:url value='/accessory/wish'>
										<c:param name="no" value="${item.itemId}" />
										<c:param name="isInWish" value="${isInWish}" /> 
										</c:url> --> <!-- onClick="wishItem(${item.itemId}, ${isInWish})" -->
										<img src="<c:url value='${like_src}' />" id="like_img${item.itemId}" alt="하트(좋아요)" class="heart"/>
									</a>
							</div> --%>
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
								<p><fmt:formatNumber value="${item.price}" pattern="###,###,###"/>원</p>
							</a>
						</div>
					</div>
				</td>

				<c:if test="${status.index % 4 == 3}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
</div>
</body>
</html>