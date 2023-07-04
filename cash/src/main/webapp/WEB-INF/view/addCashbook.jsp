<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCashbook</title>
</head>
<body>
	<h1>가계부 추가</h1>
	<form method="post" action="${pageContext.request.contextPath}/addCashbook">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>${memberId}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><input type="date" name="cashbookDate" value="${cashbookDate}" readonly="readonly"></td>
			</tr>
			<tr>
				<th>수입/지출</th>
				<td>
					<select name="category">
						<option>=== 선택하세요 ===</option>
						<option value="수입">수입</option>
						<option value="지출">지출</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>금액</th>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<th>메모</th>
				<td><input type="text" name="memo"></td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>