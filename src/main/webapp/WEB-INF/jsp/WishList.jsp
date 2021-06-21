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
		$(document).ready(function() {
			$('#allCheck').click(function(){
				$('input[name=checkWishList]:checkbox').attr('checked', true); // input type 중 name이 checkCartItem이고, type이 checkbox인 경우
				//$('#quantity'+1029).val(String(1121));
			});
			$('#allReset').click(function(){
				$('input[name=checkWishList]:checkbox').attr('checked', false); 
			});
		
		});
	</script>
	<script>
		$(document).ready(function() {
			$('#allCheck').click(function(){
				$('input[name=checkCartItem]:checkbox').attr('checked', true); // input type 중 name이 checkCartItem이고, type이 checkbox인 경우
				//$('#quantity'+1029).val(String(1121));
			});
			$('#allReset').click(function(){
				$('input[name=checkCartItem]:checkbox').attr('checked', false); 
			});
					
		});
		
	</script>
	<script>
	 $(function(){
	  var sBtn = $("ul > li");    //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
	  sBtn.find("a").click(function(){   // sBtn에 속해 있는  a 찾아 클릭 하면.
	   sBtn.removeClass("active");     // sBtn 속에 (active) 클래스를 삭제 한다.
	   $(this).parent().addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.
	  })
	 })
	</script>
	<script>
	
		function moveTarget(targetUri) {
			form.action = targetUri;
			form.submit();
		}
		
		function checkConfirm(targetUri) {
			//alert(targetUri);
			var isChk = false;
			
			var products = document.getElementsByName("checkCartItem");
			//console.log('console' + products[0]);
			for(var i = 0; i < products.length; i++) {
				if(products[i].checked == true){
					isChk = true;
					break;
				}
			}
			
			if(!isChk) {
				alert('상품을 1개 이상 선택해주세요.');
				return false;
			}
			
			moveTarget(targetUri);
		}
		
	</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container"  style="margin-left: auto; margin-right: auto; width: 90%; margin-top:5%;">
		<ul class="nav nav-tabs">
		  <li  class="#;"><a href="/wishlist/view/1">악세사리</a></li>
		  <li class="#;"><a href="/wishlist/view/2">개인거래</a></li>
		  <li class="#;"><a href="/wishlist/view/3">공동구매</a></li>
		</ul>
	</div> 
	<form method="POST" name="form"> <%-- action이 없으면 얘를 부른 컨트롤러로 넘어간다. 체크한 것만 넘어가면 돼서 Command 객체 필요 없음. form:form을 사용안 한 건 여기서 하나만 선택한 결과를 알고 싶은 게 아니고, 어떤 것들이 선택 되었는지가 중요하기 때문. 즉 path를 설정할  게 없음--%>
		<div class="container" style="margin-top: 5%;">
		<table > <!-- class="table table-hover" -->
	    <thead>
	      <tr>
	        <th>선택</th>
	        <th>상품명</th>
	        <th></th>
	        <th>가격</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="wishlist" items="${wishlist}">
	    		<tr>
	    			<c:set var="item" value="${wishlist.item}" />
	    			<td><input type="checkbox" name="checkCartItem" value="${wishlist.wishListId}" id="${wishlist.wishListId}" class="checkWish allCheckbox"/> </td>
	    			<td><img style="height: 50px;" src="<c:url value='${item.image}' />" /></td>
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
		            				<c:param name='itemId' value='${item.itemId}' /></c:url>">
		            	${item.title}</a></td> <!-- 개인거래 -->
		            </c:if>
	    			<td>
	    			<fmt:formatNumber value="${item.price}" pattern="###,###,###"/>원
	    			</td>
	    		</tr>
	    	</c:forEach>
	    </tbody>
	    </table>
	    <div id="paging">
			<c:forEach var="val" begin="1" end="${totalPageSize}"
				varStatus="status">
				<a href='<c:url value="/shop/wishList.do?page=${val}"/>'>
					<font color="black"><B>${val}</B></font>
				</a>
				<c:if test="${!status.last}">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</div>
		<div class="w3-center funcs">
         	<input type="button" value="전체 선택" id="allCheck" > 
         	<input type="button" value="전체 해제" id="allReset">
			<input type="button" value="선택 상품 모두 삭제" onClick="checkConfirm('<c:url value='/wishlit/handling/del'/>')">
			 </div>
    </div>
	</form>
</body>