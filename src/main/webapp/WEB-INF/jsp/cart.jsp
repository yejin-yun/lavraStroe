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
	<script>
		$(function() {
			$('ul li').click(function() {
				console.log('test');
				//$(this).css("background-color", "red");
				//$(this).attr('class', 'active');
				$(this).addClass('active');
			});
		});
		
/* 		 //위시리스트
        function moveTarget(targetUri) {
      		//alert(targetUri);
          form.action = targetUri; // 삭제면 삭제, 카트 이동이면 카드 이동
          form.submit();
       }
        */
      function checkConfirm(targetUri) {
   	   //alert(targetUri);
	       var isChk = false;
	       var products = document.getElementsByName("checkArtwork");
	       console.log('console' + products[0]);
	       for(var i=0;i<products.length;i++){
	           if(products[i].checked == true) {
	               isChk = true;
	               break;
	           }
	       }
	     if(!isChk) {
	    	 alert("상품을 한개 이상 선택해주세요.");
	         return false;
	     }
	     
	     moveTarget(targetUri);
      }
       
      function delWish(paraValue, targetUri) {
	        if($("input:checkbox[id='" + paraValue + "']").is(':checked') == true) {
	        	 moveTarget(targetUri);
	        } else {
	        	alert('해당 상품이 선택되어 있지 않습니다.');
	        }
	    }
      
      function moveToCart(paraValue, targetUri) {
	        if($("input:checkbox[id='" + paraValue + "']").is(':checked') == true) {
	        	 moveTarget(targetUri);
	        } else {
	        	alert('해당 상품이 선택되어 있지 않습니다.');
	        }
	    }
	</script>
</head>
<body>
	<%@ include file="header.jsp" %>
<div class="container"  style="margin-left: auto; margin-right: auto; width: 90%; margin-top:5%;">
		<ul class="nav nav-tabs">
		  <li><a href="/cart/1">악세사리</a></li>
		  <li><a href="/cart/2">개인거래</a></li>
		  <li><a href="/cart/3">공동구매</a></li>
		</ul>
	</div>
	<c:if test="${cartItemList == '' || cartItemList eq null}">
		<div style="width: 50%; margin-left: auto; margin-right: auto;">
		아직 상품 준비가 되지 않았습니다.</div>
	</c:if>
	<form method="POST"> <%-- action이 없으면 얘를 부른 컨트롤러로 넘어간다. 체크한 것만 넘어가면 돼서 Command 객체 필요 없음. form:form을 사용안 한 건 여기서 하나만 선택한 결과를 알고 싶은 게 아니고, 어떤 것들이 선택 되었는지가 중요하기 때문. 즉 path를 설정할  게 없음--%>
			<div class="container" style="margin-top: 5%;">
		<table class="table table-hover">
	    <thead>
	      <tr>
	        <th>선택</th>
	        <th>상품명</th>
	        <th></th>
	        <th>가격</th>
	        <th>수량</th>
	        <th>합계</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:set var="allTotalCost" value="0" />
	    	<c:forEach var="cartItem" items="${cartItemList}">
	    		<c:set var="categoryId" value="${cartItem.categoryId}" />
	    		<tr>
	    			<c:set var="item" value="${cartItem.item}" />
	    			<c:set var="itemTotalCost" value="${item.price * cartItem.quantity}" />
	    			<td><input type="checkbox" name="checkCartItem" value="${cartItem.cartItemId}" id="${cartItem.cartItemId}" class="checkWish allCheckbox"/> </td>
	    			<td><img style="height: 50px;" src="<c:url value='${item.image}' />" /></td>
	    			<td>${item.title}</td>
	    			<td>${item.price}</td>
	    			<td>${cartItem.quantity}</td>
	    			<td>${itemTotalCost}</td>
	    			<c:set var="allTotalCost" value="${allTotalCost + itemTotalCost}" />
	    		</tr>
	    	</c:forEach>
	    </tbody>
	    </table>
	    <div id="paging">
			<c:forEach var="val" begin="1" end="${totalPageSize}"
				varStatus="status">
				<a href='<c:url value="/cart/${categoryId}?page=${val}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
		<div class="w3-center funcs">
         	<input type="button" value="전체 선택" id="allCheck" > 
         	<input type="button" value="전체 해제" id="allReset">
			<input type="button" value="선택 상품 모두 삭제" onClick="checkConfirm('<c:url value='/user/deletewishlist' />')">
			<input type="button" value="선택 상품 장바구니 이동" onClick="checkConfirm('<c:url value='/user/fromWishToCart' />')">
		 </div>
    </div>
	</form>
</body>