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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>${item.title} 페이지</title>
<style>
	html, body {
		margin: 0;
		padding: 0;
		height: 100%;
	}
	td {
		text-align: center;
	}
	#main_table {
		border-collapse: separate;
		border-spacing: 0 80px;
		/* border: 1px solid red; */
	}
	#content_table{
	 	border-collapse: separate; /* 이상하게 이거 없으면 적용이 안되네.. */
		width: 100%; 
		/* padding-top: 10%;  */
		/* padding-bottom: 10%; */
		margin: 5% auto;
		border-spacing: 0 30px; /* %로 주면 안됨. margin-bottom: 20px;도 안됨 */
	}
	.content_img{
		width: 400px;
		height: 300px;
	}
/*review 부분 추가*/
.rating {
	float : left;
 	display: flex;
    flex-direction: row-reverse;
}

.rating>input {
    display: none
}

.rating>label {
    position: relative;
    width: 50px;
    font-size: 30px;
    color: #FFD600;
    cursor: pointer
}

.rating>label::before {
    content: "\2605";
    position: absolute;
    opacity: 0
}

.rating>label:hover:before,
.rating>label:hover~label:before {
    opacity: 1 !important
}

.rating>input:checked~label:before {
    opacity: 1
}

.rating:hover>input:checked~label:before {
    opacity: 0.4
}

.cover {
    display: flex;
    justify-content: space-between;
}

</style>
<script>

	function updateQuantity(num, stock, price, ths) { //num이 -1이면 감소버튼 누른거고, 1이면 증가 버튼 누른 거.
		var input = document.getElementById("quantity");
		var span = document.getElementById("totalCost");
		//alert(span.innerHTML);
		//var total = Number(span.innerHTML);
		var total = Number($('#hdTotalCost').val());

		var cnt = Number(input.value);	// 글자여도 에러는 안나고 NaN이 반환됨.

		if(isNaN(cnt)) {
			//alert(cnt);
			alert('옳지 않은 형식입니다.');
			return false;
		}
		//alert(stock);
		
		if(num == -1) {
			if(cnt == 1) {
				alert('최소 1개 이상 주문하셔야 합니다.');
				return false;
			}
			if(cnt > 0){
				input.value = Number(cnt)-1;
				total = total - price;
				
			}
		}
		if(num == 1) {
			if(cnt == stock) { //현재 주문하려는 상품의 수가 stock과 동일하니 더 증가 시킬 수 없음.
				alert('현재 재고가 충분하지 않습니다.');
				return false;
			}
			if(cnt < stock) {
				input.value = Number(cnt)+1;
				total += price;
			}
		}
		
		var temp = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); //https://mizzo-dev.tistory.com/entry/JavaScript%EC%A0%95%EA%B7%9C%EC%8B%9D-%EC%88%AB%EC%9E%90%EC%97%90-1000%EB%8B%A8%EC%9C%84%EB%A1%9C-%EC%BD%A4%EB%A7%88%EC%89%BC%ED%91%9C-%EA%B5%AC%EB%B6%84%EC%9E%90-%EB%84%A3%EA%B8%B0 //https://likejirak.tistory.com/31
		span.innerHTML = temp;
		
		$('#hdTotalCost').val(total); //document.getElementById('hdTotalCost').value는 에러났음. 
		//alert($('#hdTotalCost').val());
	}
	
	function moveTarget(targetUri) {
		//alert(targetUri);
		form1.action = targetUri;  //쿼리스트링 사라짐. get은 사라지는데, post는 사라지지 않음. https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=damuri1&logNo=100157143687
		form1.submit();
	}

	function moveAddCart(itemId, stock, quantityInCart) {
		var user = confirm("카트에 동일한 상품이 있습니다. 추가하시겠습니까?");
		var quantity = Number(document.getElementById("quantity").value);
		quantityInCart = Number(quantityInCart)
		if(quantity +  quantityInCart> stock) {
			//alert(quantity + quantityInCart);
			//alert(stock);
			alert('현재 재고가 충분하지 않습니다.');
			return false;
		}
		
		if(user) {
			var url = "/item/cart_uq?no=" + itemId + "&q=" + quantity;
			
			moveTarget(url); //여기에 직접 넣으면 파라미터가 -1로 전달됨. --> get으로 전달해서 그랬음. 
			
			// 카트로 이동하면 DB에 업데이트가 안됨
			alert("카트에 추가했습니다.");
		}
	}
	
	function buyItem(itemId, stock) {
		var quantity = document.getElementById("quantity").value;
		if(quantity > stock) {
			alert('현재 재고가 충분하지 않습니다.');
			return false;
		}
		var url = "/item/buy?no=" + itemId + "&q=" + quantity;
		
		moveTarget(url);
	}
	
	function moveNewCart(itemId, stock){
		//var msg = itemId; //itemId 하나만 json으로 보낼때. 아래 방식은 객체라서  int로 변환 못시킴	
		if(document.getElementById("quantity").value > stock) {
			alert('현재 재고가 충분하지 않습니다.');
			return false;
		}
		
		var msg = {
			"item":{"itemId":itemId},
			"quantity": document.getElementById("quantity").value
		}
		var jsonStr = JSON.stringify(msg);
	
		$.ajax({
			type: "POST",
			url: "/item/cart",
			contentType : "application/json",
			data: jsonStr,
			processData: false,
			success: function(response) {
				if(response == "success") {
					var user = confirm("카트에 추가했습니다. 이동하시겠습니까?");
					if(user) {
						moveTarget("/cart/view/1");
					} else {
						history.go();
					}
				}
				else if(response == "LoginForm") {
					moveTarget("/shop/loginForm.do");
				}
				else {
					alert('카트에 추가하는 것을 실패했습니다'); //사실상 failed면 error:에서 처리됨. 
				}
				
			},
			error: function(){
				alert("ERROR", arguments);
			}
		});
	}
	
	function moveWish(itemId){
		//var msg = itemId; //itemId 하나만 json으로 보낼때. 아래 방식은 객체라서  int로 변환 못시킴	
		
		var msg = itemId;
		var jsonStr = JSON.stringify(msg);
	
		$.ajax({
			type: "POST",
			url: "/item/wish",
			contentType : "application/json",
			data: jsonStr,
			processData: false,
			success: function(response) {
				if(response == "success") {
					alert("위시리스트에 추가했습니다.");
				}
				else if(response == "LoginForm") {
					moveTarget("/shop/loginForm.do");
				} else if(response == "exist") {
					alert('이미 위시리스트에 담긴 상품입니다.');
				}
				
			},
			error: function(){
				alert("ERROR", arguments);
			}
		});
	}
</script>
</head>
<body>
<%@ include file="header.jsp" %>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:if test="${dItem == '' || dItem eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">상품 로딩 중 문제가 발생하였습니다.</div>
</c:if>
<c:if test="${errReview}">
		<script>
			var msg = '<c:out value="${errMsg}"/>';
			alert(msg);
		</script>
</c:if>
<div align="center" style="width : 90%; margin-left:auto; margin-right: auto; margin-bottom: 10%;">
	<table id="main_table" style="width: 80%; "> <%-- padding-bottom: 15%; --%>
		<tr>
			<td>
				<img src="<c:url value='${dItem.item.image}' />" width="400px" height="300px">
			</td>
			<td>
				<form name="form1" class="btn" method="post">
				<table style="height: 300px; width: 500px; margin: 0 auto; border-collapse:collapse; border: none;">
					<tr align="center">
						<td>상품명</td>
						<td>${dItem.item.title}</td>
					</tr>
					<tr align="center">
						<td>개당 가격</td>
						<td><fmt:formatNumber value="${dItem.item.price}" pattern="###,###,###"/>원</td>
					</tr>
					<tr align="center">
					
					</tr>
					
					<tr align="center">
						<td> 배송비 </td>
						<td> 3,000원 (20만원 이상 구매시 무료배송)</td>
					</tr>
					<tr align="center">
						<td> 수량 </td>
						<td> 
						<a href="#" onClick="updateQuantity(-1, ${dItem.item.quantity}, ${dItem.price}, this)"><img id="down" src="<c:url value='/images/item/btn_count_down.gif' />"/></a>
						<input type="text" id="quantity" value="${dItem.quantity}" name="quantity" size="2" style="text-align:center;"/>
						<a href="#" onClick="updateQuantity(1, ${dItem.item.quantity}, ${dItem.price}, this)"><img id="up" src="<c:url value='/images/item/btn_count_up.gif' />" /></a>
						</td>
					</tr>
					<tr align="center">
						<td> 총금액 </td>
						<td> 
							<input type="hidden" id="hdTotalCost" name="totalCost" value="${dItem.itemTotalCost}" />
							<span id="totalCost"><fmt:formatNumber value="${dItem.itemTotalCost}" pattern="###,###,###"/></span>원 
						</td>
					</tr>
					
					<tr align="center"> <!-- 버튼들..  -->
						<td colspan="2">
								<%-- ${dItem.item.isInWishlist} 계속 0으로 나옴. 값이 저장되어 있지 않나봄--%>
								<button type="button" id="wish" onClick="moveWish(${dItem.item.itemId})">위시리스트</button>
								<%-- <c:choose>
									<c:when test="${dItem.item.isInWishlist == 0}">
										<a href="<c:url value='/item/wish'><c:param name='no' value='${dItem.item.itemId}' /></c:url>">
											<button type="button" id="wish" onClick="moveWish(${dItem.item.itemId})">위시리스트</button>
										</a>
									</c:when>
									<c:when test="${dItem.item.isInWishlist == 1}">
										<button type="button" id="wish" onClick="alert('이미 위시리스트에 담긴 상품입니다.')">위시리스트</button>
									</c:when>
								</c:choose> --%>
								<c:choose>
									<c:when test="${dItem.item.isInCart == 0}">
										<%-- <a href="<c:url value='/item/cart?=no${dItem.item.itemId}' />"> --%>
											<button type="button" id="cart" onClick="moveNewCart(${dItem.item.itemId}, ${dItem.item.quantity})">카트담기</button>
										<!-- </a> -->
									</c:when>
									<c:when test="${dItem.item.isInCart != 0}">
										<button type="button" id="cart" onClick="moveAddCart(${dItem.item.itemId}, ${dItem.item.quantity}, ${quantityInCart})">카트담기</button>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${dItem.item.isSoldout == 0}">
										<%-- <a href="<c:url value='/item/buy?no=${dItem.item.itemId}&q=${dItem.item.quantity}' />"> --%>
											<button type="button" id="buy" onClick="buyItem(${dItem.item.itemId}, ${dItem.item.quantity})">바로구매</button>
										<!-- </a> -->
									</c:when>
									<c:when test="${dItem.item.isSoldout == 1}">
										<button type="button" id="buy">품절</button>
									</c:when>
								</c:choose>
						</td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan='2' style="text-align:left;">
				<label> 
					<h3>| Content </h3>
				</label>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<table id="content_table"> <%-- 하나의 tr에 text, 사진 등 넣을 것 --%> 
					<tr>
						<td>
							${dItem.item.description}
						</td>
					</tr>
					<tr>
						<td>
							<img src="<c:url value='${dItem.item.image}' />" class="content_img">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>



<!-- 리뷰 부분 추가! -->

<div style="width : 70%; margin : 5% auto; padding-bottom: 10%;">
	<form class="wc-center" method="post" action="<c:url value='/accessory/insertReview.do'/>">
		<input type="hidden" name="itemId" value="${dItem.item.itemId}">
		<div class="form-group">
			<label for="reviewContent"> 
				<h3>| Review </h3>
			</label>
    		<div style="width : 90%">
    			<div class="rating"> 
					<input type="radio" name="rating" value="5" id="5"> <label for="5">☆</label>
					<input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
					<input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
					<input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
					<input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
				</div>
    		</div>
    		<table class="review">
    			<tr>
    				<td>
    					<input type="text"  style="width: 780px; height:45px; font-size:20px; float:left;" class="form-control" name="content" placeholder="한 줄 리뷰를 입력해주세요. (최소 10글자)"> 
    					<font color="white"> <input type="submit" type="button" style="float:left; margin-left : 10px;"class="btn btn-secondary btn-lg" value="등록"/> </font>
    				</td>
    				
    			</tr>
    			<tr>
    				<td><small class="form-text text-muted" style="text-align : left;">리뷰는 해당 아이템을 구매한 회원에 한해서 작성할 수 있습니다. </small></td>
    			</tr>
    		</table>
    		<br/>
    		<table style="width : 100%;">
    			<tr>
    				<td>
		   				<c:if test="${fn:length(reviewList) eq 0}">
		   					<td><div style="text-align: center;"><h5>현재 상품은 작성된 리뷰가 없습니다! 첫 리뷰를 작성해 보세요.</h5></div></td>
		   				</c:if>
		   				<c:if test="${fn:length(reviewList) != 0}">
		   					<c:forEach var="review" items="${reviewList}" varStatus="status">
		   					
   								<ul class="list-group list-group-flush" style="width : 100%;">
									<li class="list-group-item" > 
										<div class="cover">
											<div>
												<label> ${review.memberId}님 </label> &nbsp; &nbsp;
												<font color="gray">${review.reviewDate }</font> 
											</div>
											<div>
												<c:if test="${review.memberId eq memberId }">
													<a href="<c:url value='/accessory/deleteReview.do'> 
																<c:param name="reviewId" value="${review.reviewId }" />
																<c:param name="itemId" value="${review.itemId }" />
															</c:url>">
														<font color="red"><b> X </b></font>
													</a>
												</c:if>
											</div>
										</div>
									</li>
									<li class="list-group-item" >
										<div style="white-space: nowrap; text-align : left;">
										 <label>
											<c:if test="${review.preference eq 1 }">★☆☆☆☆</c:if>
											<c:if test="${review.preference eq 2 }">★★☆☆☆</c:if>
											<c:if test="${review.preference eq 3 }">★★★☆☆</c:if>
											<c:if test="${review.preference eq 4 }">★★★★☆</c:if>
											<c:if test="${review.preference eq 5 }">★★★★★</c:if>
										</label> : &nbsp;
										${review.reviewContent }</div>
									</li> 
								</ul>
   							
							</c:forEach>
						</c:if>
					</td>
				</tr>
    		</table>
		</div>
	</form>
</div>
</body>
</html>