<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember.jsp</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form method="post" action="${pageContext.request.contextPath}/modifyMember">
		<table border="1">
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">정보수정</button>
	</form>
</body>
</html>