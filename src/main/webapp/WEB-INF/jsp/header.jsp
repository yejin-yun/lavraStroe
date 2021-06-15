
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>

	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 <meta http-equiv="Cache-Control" content="max-age=0">
	 <meta http-equiv="Cache-Control" content="no-cache">
	 <meta http-equiv="expires" content="0">
	 <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
	 <meta http-equiv="Pragma" content="no-cache">
     <!--JQuery -->
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="<c:url value='/js/base.js' />" ></script>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link type="text/css" rel="stylesheet" href="<c:url value='/style/base.css' />" >

</head>
<body>
<header>
       <div class="search_header ">
            <a href="#"><img id="serch_btn" src="<c:url value='/images/bagic/magnifier-32.png' />"  style="padding-left: 40px; padding-top: 10px;"></a>
            <form method="post" action="<c:url value='/shop/searchProducts.do' />" style="display: inline-block; margin-left: 20px; margin-top:20px; width:80%; height: 50%;">
                <input type="text" name="searchKey" class="keyword" placeholder="상품명을 검색하세요" style="width:100%; height: 100%; border:0; background:#f3f3f3 " >
            </form>
        </div>
         <div class="wrapper" style="z-index:1000; margin-left: auto; margin-right: auto; width: 90%;"> <%--style="z-index:1000; margin-left: auto; margin-right: auto; width: 90%;" z-index를 왜 넣었었지..? --%>
        <h1 id="logo"><a href="<c:url value='/' />" title="Home"><img src="<c:url value='/images/bagic/lavra_logo.PNG' />" alt="lavra" ></a></h1>
        <nav>
            <h2><strong>
            <ul class="main_menu"> <!-- style="postion:relative;" -->
                <li id="work"><a>악세사리</a>
                    <ul class="work_sub_menu sub_menu" style="z-index:1000; position:absolute;">
                        <li><a href="<c:url value='/accessory/earring/1' />">귀걸이</a></li> 
                        <li><a href="<c:url value='/accessory/necklace/1' />">목걸이</a></li>
                        <li><a href="<c:url value='/accessory/ring/1' />">반지</a></li>
                        <li><a href="<c:url value='/accessory/bracelet/1' />">팔찌/발찌</a></li>
                        <li><a href="<c:url value='/accessory/hairdeco/1' />">헤어장식</a></li>
                    </ul>
                </li>
                <li id="work1"><a>개인 거래</a>
                    <ul class="work_sub_menu1 sub_menu" style="z-index:1000; position:absolute;">
                        <li><a href="<c:url value='/accessory/earring/1' />">귀걸이</a></li> 
                        <li><a href="<c:url value='' />">목걸이</a></li>
                        <li><a href="<c:url value='' />">반지</a></li>
                        <li><a href="<c:url value='' />">팔찌</a></li>
                        <li><a href="<c:url value='' />">헤어장식</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value='/group' />">공동구매</a></li>
            </ul></strong>
            </h2>
        </nav>
        <table>
         <tr>
	      </tr>
		  <tr>
	      	 <div class="search_btn"><a href="#"><img id="search_img" src="<c:url value='/images/bagic/magnifier-32.png' />"></a></div>
	      </tr>
	       <tr>
	        <div class="menu_header">
	            <!-- https://ddorang-d.tistory.com/104 -->
	            <div class="menubar_btn"><a href="#">
	                <img id="menubar" src="<c:url value='/images/bagic/menubar.PNG' />" border="0">
	            </a>
	            </div>   
	        </div>
	       </tr>
        <div class="menu_bg" style="z-index:1000;"></div>
        <div class="sidebar_menu" style="z-index:1000;">
            <div class="close_btn"><a href="#">
                <img src="<c:url value='/images/bagic/x-mark.png' />" border="0" width="10px" height="10px">
            </a></div>
            <ul class="menu_wrap"> <%-- 메뉴 펼쳤을 때 배경 어두워지는 js 주석처리해둠. wrapper class div를 중앙 처리해서   --%>
            	
                <li><a href="<c:url value='/cart/view/1' />">Cart</a></li>
                
                <c:if test="${empty userSession.member}" >
                <li>
		        	<a href="<c:url value="/shop/loginForm.do"/>">login</a>
		      	</li>
		      	</c:if>

		      	<c:if test="${!empty userSession.member}" >
		      		<li>
		        		<a href="<c:url value="/shop/logout.do"/>">logout</a>
                	</li>
		      		<li>
		        		<a href="<c:url value="/shop/editAccount.do"/>">Mypage</a>
		         	</li>
		         	<li><a href="<c:url value='/shop/wishList.do' />">Wishlist</a></li>
                
		      	</c:if>
                
                
            </ul>
        </div>
        </table>
        
    </div>
    </header>
</body>
</html>