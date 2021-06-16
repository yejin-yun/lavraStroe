<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Order</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
        .menu a {cursor:pointer;}
        .menu .submenu {display:none;}
        .pm_nobackbook_apperance {display:none;} /* , .pm_card_apperance */
        #container{
        	display: inline-block;
        	width: 100%;
        	margin-top: 5%;
        }
        .menu li {
        	margin: 10px;
        }
        .recipient_info {
        	display: inline;
        	width: 80%;
        	/* margin-top: 30px; */
        	/* margin-bottom: 30px; */
        }
        label{ /*251p 이거 덕분에 입력창이 예쁘게 맞춰짐.*/ 
			display: inline-block;
			width: 120px;
		}
		
		.pay{
			margin: 0 10px;
		}
		.submenu{
			border-top: none 1px black;
		}
		.submenu li {
			padding: 10px;
			border-bottom: none 1px black;
		}
		.product {
			width: 100%;
		}
		.product label {
			width: 100%;
		}
		.product span {
			margin: 0 10px;
		}
		.item_img {
			 width : 100px;
			 height : 80px; 
		}
		.interval {
			margin: 3% 0;
		}
		.funcs {
			margin-top: 5%;
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
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
     <script src="<c:url value='/js/base.js' />" ></script>

   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $(".menu > a").click(function(){
                var submenu = $(this).next("ul");
                if( submenu.is(":visible") ){
                    submenu.slideUp();
                }else{
                    submenu.slideDown();
                }
            });
        });

        /* http://blog.naver.com/PostView.nhn?blogId=schatz1234&logNo=220908487291&parentCategoryNo=&categoryNo=16&viewDate=&isShowPopularPosts=true&from=search */
        $(function() {
            $(".pm_nobackbook_click").click(function(){
                $(".pm_card_apperance").css("display", "none");
                if( $(".pm_nobackbook_apperance").is(":visible") ){
                    $(".pm_nobackbook_apperance").slideUp();
                }else{
                    $(".pm_nobackbook_apperance").slideDown();
                }
            });
            $(".pm_card_click").click(function(){
                $(".pm_nobackbook_apperance").css("display", "none");
                if( $(".pm_card_apperance").is(":visible") ){
                    $(".pm_card_apperance").slideUp();
                }else{
                    $(".pm_card_apperance").slideDown();
                }
            });
         
        });
    </script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div id="container" class="w3-center">
			<c:set var="targetUrl"><c:url value="/item/orderSubmit/${item.itemId}/${isInCart}" /></c:set>
			<form:form method="POST" action="${targetUrl}" modelAttribute="itemOrder">
				<form:errors cssClass="error" />
				<div class="menu interval">
					<h3>상품 정보</h3>
                    <a><button type="button" class="btn btn-info" id="submenu_click" style="margin-top: 10px;">주문 상품 보기</button></a>
					<ul class="submenu"> <!--해당 리스트의 내용을 display:none;을 통해 안보이게 처리를 한 후 -->
                    	<c:if test="${isInCart == 0}" >
                    		<li>
                    			<div class="product">
                    				<label>
                    					<input type="checkbox" name="payment_product" value="${item.itemId}" onClick="return false;" checked>
                    					<a href="<c:url value='/accessory/detail'>
                    						<c:param name='no' value='${item.itemId}' /></c:url>" >
                    						<img src="<c:url value='${item.image}' />" class="item_img"/>
                    						<span>상품명: ${item.title}</span><span>가격: <fmt:formatNumber value="${item.price}" pattern="###,###,###"/>원</span><span>수량: ${quantity}</span>
                    					</a>
                    				</label>
                    			</div>
                    			<c:set var="totalCost" value="${item.price * quantity}" />
                    		</li>
                    	</c:if>
                    	<c:if test="${isInCart == 1}" >
                    		<c:forEach var="ci" items="${cartItemList}" varStatus="status">
                    			<c:set var="totalCost" value="${totalCost + ci.item.price * ci.quantity}" />
                    			<li>
                    			<div class="product">
                    				<label>
                    					<input type="checkbox" name="payment_product" value="${ci.item.itemId}" onClick="return false;" checked >
                    					<a href="<c:url value='/accessory/detail'>
                    						<c:param name='no' value='${ci.item.itemId}' /></c:url>" >
                    						<img src="<c:url value='${ci.item.image}' />" class="item_img" />
                    						<span>상품명: ${ci.item.title}</span><span>가격: <fmt:formatNumber value="${ci.item.price}" pattern="###,###,###"/>원</span><span>수량: ${ci.quantity}</span>
                    					</a>
                    				</label>
                    			</div>
                    		</li>
                    		</c:forEach>
                    	</c:if>
                    </ul>
				</div>		
		</div>
		<div id="recipient_info" class="w3-center interval">
			<p><font color="blue"><B>주문자 정보</B></font></p><br> 
			<div class="form-group form-inline">
				<label for="shipname">주문자 성명</label>
    			<form:input path="shipname" class="form-control" placeholder="Enter Name" />
			</div>	
			<div class="form-group form-inline">
				<label for="shipZip">우편 번호</label>
    			<form:input class="form-control" path="shipZip" placeholder="Enter Zip" />
			</div>		
			<div class="form-group form-inline">
				<label for="shipAddr1">배송지 주소1</label>
    			<form:input path="shipAddr1" class="form-control"  placeholder="Enter Address1" />
			</div>		
			<div class="form-group form-inline">
				<label for="shipAddr2">배송지 주소2</label>
    			<form:input path="shipAddr2" class="form-control"  placeholder="Enter Address2" />
			</div>	
		</div>
		<div id="payment_info" class="w3-center interval">
			 <p><font color="blue"><B>결제 정보</B></font></p><br> 
		 	<form:radiobutton path="payType" value="0" label="무통장 입금" class="pm_nobackbook_click pay" />
			<form:radiobutton path="payType" value="1" label="카드 결제" class="pay pm_card_click" checked="true" />
            
            <div>
            	<ul class="pm_nobackbook_apperance" >
                	<li>
                		<div class="form-group form-inline">
	                    	<label>입금자명     </label>
	                    	<input type="text" name="depositor" class="form-control"/>
                    	</div>
                    </li>
                    <li>
                    	<div class="form-group form-inline">
	                        <label>입금은행     </label>
	                        <form:select path="bankType" items="${bankTypes}" class="form-control"/>
                        </div>
                    </li>
                </ul>  
                <ul class="pm_card_apperance" >
                	<li>
                		<div class="form-group form-inline">
	                    	<label>Card Type: </label>
	                    	<form:select path="cardType" items="${creditCardTypes}" class="form-control"/>
                    	</div>
                    </li>
                    <li>
                    	<div class="form-group form-inline">
	                        <label>Card Number: </label>
	                        <form:input path="cardNum" class="form-control" placeholder="ex : 9999 9999 9999 9999 "/>
                        </div>
                    </li>
                    <li>
                    	<div class="form-group form-inline">
	                    	<label>Expiry Date: </label>
	                    	<form:input path="expiryDate" class="form-control" placeholder="ex : (MM/YY)"/>
                    	</div>
                    </li>
                </ul>  
	           </div>   
	           <div style="margin: 20px;" class="well-sm interval" >
	                <span>최종 결제 금액 </span> 
	                <c:if test="${totalCost < 200000}">
						<c:set var="deliver" value="3000" />
						<B>(배송비 3,000원)</B>:
					</c:if>
					<c:if test="${totalCost >= 200000}">
						<c:set var="deliver" value="0" />
						<B>(배송비 무료)</B>: 
					</c:if>
	                <fmt:formatNumber value="${totalCost + deliver}" pattern="###,###,###"/>원
	                <p><font color="red">20만원 이상 구매시 무료 배송!!</font></p>
	           </div>
	           <div class="w3-center interval funcs" style="padding-bottom: 20%;">
		  			<input type="button" value="취소" style="padding-right: 10px;">
		  			<input type="submit" id="btn_buy" value="구매">
	  			</div>
        	</form:form>
		</div>
    </section>
</body>
</html>
