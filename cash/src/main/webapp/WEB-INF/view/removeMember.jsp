<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
		<table border="1">
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">탈퇴하기</button>
	</form>
</body>
</html>