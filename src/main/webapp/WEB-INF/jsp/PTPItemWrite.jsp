<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lavra: ptp item insert</title>

<script>
	$(document).ready(function(){
		$("#add").click(function(){
			var ItemName = $("#title").val();
			var productName = $("#productName").val();
			var productPhoto = $("#image").val();
			var productDesc = $("#description").val();
			var productPrice = $("#price").val();
			var productdevP = $("#devP").val();
			var productBank = $("#bank").val();
			var productAccount = $("#account").val();
			
			if(ItemName == "") {
				alert("상품명을 입력해주세요");
				ItemName.foucs();
			} else if (productName == "") {
				alert("상품 종류를 선택해주세요");
				productName.focus();
			} else if (productPhoto == "") {
				alert("상품 사진을 입력해주세요");
				productPhoto.focus();
			} else if (productDesc == "") {
				alert("상품 설명을 입력해주세요");
				productDesc.focus();
			}else if (productPrice == "") {
				alert("상품 가격을 입력해주세요");
				productPrice.focus();
			}else if (productdevP == "") {
				alert("배송비를 입력해주세요");
				productdevP.focus();
			}else if (productBank == "") {
				alert("은행 정보를 입력 해주세요");
				productAccount.focus();
			}else if (productAccount == "") {
				alert("계좌 번호를 입력 해주세요");
				productAccount.focus();
			}
			document.form.action = "/shop/productinsert.do";
			document.form.submit();
		});
	});
	function backPage() {
		history.back();
	}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<form id = "form" name="form" enctype="multipart/form-data" method="POST">
		<table >
			<thead>
				<tr>
					<th colspan="2">제품 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<td>상품명</td>
					<td>
						<input type="text" class="form-control" name="title"
							placeholder="상품명을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<td>종류</td>
					<td>
					<!-- 
						<select name="productName">
							<option value="">종류 선택</option>
							<option value="earring">귀걸이</option>
							<option value="necklace">목걸이</option>
							<option value="ring">반지</option>
							<option value="bracelet">팔찌</option>
							<option value="harideco">머리장식</option>
						</select>
					 -->
						<select path="productName" items="${productList} class="form-control" />
					</td>
				</tr>
				<tr>
					<td>사진</td>
					<td>
						<input type="file" class="form-control-file" name="image">
					</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>
						<input type="text" class="form-control" name="description"
							placeholder="제품 설명란을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input type="number" class="form-control" name="price"
							placeholder="제품 가격을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td>
						<input type="number" class="form-control" name="devP"
							placeholder="배송비를 입력해주세요.">
					</td>
				</tr>
				<tr>
					<th colspan="2">계좌 정보</th>
				</tr>
				<tr>
					<td>은행</td>
					<td>
						<input type="text" class="form-control" name="bank"
							placeholder="계좌가 등록된 은행명을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<td>계좌번호</td>
					<td>
						<input type="number" class="form-control" name="account"
							placeholder="계좌번호를 입력해주세요.">
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<input type="submit" value="업로드" />
			<input type="button" value="취소" OnClick="backPage()" />
		</div>
	</form>
</body>
</html>