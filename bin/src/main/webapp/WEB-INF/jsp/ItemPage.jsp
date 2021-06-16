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
<title>${item.title} 페이지</title>
<script>

	function updateQuantity(num, stock, price, ths) {
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
			if(cnt == stock) {
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

	function moveAddCart(itemId) {
		var user = confirm("카트에 동일한 상품이 있습니다. 추가하시겠습니까?");
		
		if(user) {
			var url = "/item/cart_uq?no=" + itemId + "&q=" + document.getElementById("quantity").value;
			
			moveTarget(url); //여기에 직접 넣으면 파라미터가 -1로 전달됨.
			
			var temp = confirm("카트에 추가했습니다. 이동하시겠습니까?");
			if(temp) {
				moveTarget("/cart/view/1");
			}
		}
	}
	
	function moveNewCart(itemId){
		//var msg = itemId; //itemId 하나만 json으로 보낼때. 아래 방식은 객체라서  int로 변환 못시킴
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
					}
				}
				else if(response == "LoginForm") {
					moveTarget("/shop/loginForm.do");
				}
				else {
					alert('카트에 추가하는 것을 실패했습니다');
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
<div align="center" style="width : 90%;">
	<table border="1">
		<tr>
			<td>
				<img src="<c:url value='${dItem.item.image}' />" width="340" height="300">
			</td>
			<td>
				<form name="form1" class="btn" method="post">
				<table border="1" style="height: 300px; width: 400px;">
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
						<td> 3,000원 </td>
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
								<a href="<c:url value='/item/wish?=no${dItem.item.itemId}' />">
									<button type="button" id="wish">위시리스트</button>
								</a>
								<c:choose>
									<c:when test="${dItem.item.isInCart == 0}">
										<%-- <a href="<c:url value='/item/cart?=no${dItem.item.itemId}' />"> --%>
											<button type="button" id="cart" onClick="moveNewCart(${dItem.item.itemId})">카트담기</button>
										<!-- </a> -->
									</c:when>
									<c:when test="${dItem.item.isInCart != 0}">
										<button type="button" id="cart" onClick="moveAddCart(${dItem.item.itemId})">카트담기</button>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${dItem.item.isSoldout == 0}">
										<a href="<c:url value='/item/buy?=no${dItem.item.itemId}' />">
											<button type="button" id="buy">바로구매</button>
										</a>
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
	</table>
</div>
</body>
</html>