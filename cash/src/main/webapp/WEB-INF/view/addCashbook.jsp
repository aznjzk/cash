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
				<td>날짜</td>
				<td><input type="date" name="cashbookDate" value="${cashbookDate}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td><input type="text" name="category"></td>
			</tr>
			<tr>
				<td>금액</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" name="memo"></td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>