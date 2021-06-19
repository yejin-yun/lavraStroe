<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lavra: ptp item update</title>

<script>
	function backPage() {
		history.back();
	}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<form name="form" method="POST" action="<c:url value='/sellList/view/update.do' />">
		<table border="1">
			<thead>
				<tr>
					<th colspan="2">제품 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<td>상품명</td>
					<td>
						${pitem.item.title}
					</td>
				</tr>
				<tr>
					<td>사진</td>
					<td>
						${pitem.item.image}
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
						${pitem.devP}
					</td>
				</tr>
				<tr>
					<th colspan="2">계좌 정보</th>
				</tr>
				<tr>
					<td>은행</td>
					<td>
						${pitem.bank}
					</td>
				</tr>
				<tr>
					<td>계좌번호</td>
					<td>
						${pitem.account}
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