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
<title>${gitem.item.title} 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
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

</head>
<body>
<%@ include file="header.jsp" %>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:if test="${gitem == '' || gitem eq null}">
		<div style="width: 50%; margin-left:auto; margin-right: auto;">상품 로딩 중 문제가 발생하였습니다.</div>
</c:if>

<c:if test="${errReview}">
		<script>
			var msg = '<c:out value="${errMsg}"/>';
			alert(msg);
		</script>
</c:if>
<div class="w3-center" style="width: 80%; margin : auto;">
	<table class="table table-bordered">
		<tr>
			<td rowspan="8">
				<img src="${gitem.item.image}" width="340" height="300">
			</td>
		</tr>
		<tr align="center">
			<td>상품명</td>
			<td>${gitem.item.title}</td>
		</tr>
		<tr align="center">
			<td>개당 가격</td>
			<td><fmt:formatNumber value="${gitem.item.price}" pattern="###,###,###"/></td>
		</tr>
		<tr align="center">
			<td>상품소개</td>
			<td>${gitem.item.description}</td>
		</tr>
		
		<tr align="center">
			<td> 결제일(마감일) </td>
			<td> ${gitem.paymentDate}</td>
		</tr>
		<tr align="center">
			<td> 현재 달성율 </td>
			<td> ${gitem.percent}</td>
		</tr>
		
		<tr align="center">
			<td> 진행 상태 </td>
			<c:choose>
  				<c:when test="${gitem.status == 0}">
     				<td>진행 중</td>
 				</c:when>
 				<c:when test="${gitem.status == 1}">
     				<td> 마감 성공 (100% 달성)</td>
 				</c:when>         
				<c:otherwise>
  					 <td> 마감 실패 (100% 미달성) </td>
 				</c:otherwise>
			</c:choose>
		</tr>
		
		<tr align="center">
			<td colspan="2">
				<form name="form1" method="post" action="<c:url value='/group/newOrder.do'/>">
					<input type="hidden" name="itemId" value="${gitem.item.itemId}">
					<select name="amount">
						<c:forEach begin="1" end="10" var="i">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>&nbsp;개
					&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${gitem.status == 0}"> <!-- 진행 중인 공구만 신청가능 -->
						<input type="submit" value="구매 신청하기">
					</c:if>
					<c:if test="${gitem.status != 0}">
						<input type="submit" value="마감되었습니다" disabled>
					</c:if>
				</form>
			</td>
		</tr>
	</table>
</div>
<br/>
<div style="width : 80%; margin : 0 auto;">
	<form class="wc-center" method="post" action="<c:url value='/group/insertReview.do'/>">
		<input type="hidden" name="itemId" value="${gitem.item.itemId}">
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
    				<td><small class="form-text text-muted">리뷰는 해당 아이템을 구매한 회원에 한해서 작성할 수 있습니다. </small></td>
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
													<a href="<c:url value='/group/deleteReview.do'> 
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
										 <label>
											<c:if test="${review.preference eq 1 }">★☆☆☆☆</c:if>
											<c:if test="${review.preference eq 2 }">★★☆☆☆</c:if>
											<c:if test="${review.preference eq 3 }">★★★☆☆</c:if>
											<c:if test="${review.preference eq 4 }">★★★★☆</c:if>
											<c:if test="${review.preference eq 5 }">★★★★★</c:if>
										</label> : &nbsp;
										${review.reviewContent }
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