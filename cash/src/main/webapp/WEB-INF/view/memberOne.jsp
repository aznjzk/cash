<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne.jsp</title>
</head>
<body>
	<h1>회원 상세 정보</h1>
	<table border="1">
			<tr>
				<td>아이디</td>
				<td>${memberOne.memberId}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${memberOne.memberPw}</td>
			</tr>
			<tr>
				<td>가입일자</td>
				<td>${memberOne.createdate}</td>
			</tr>
			<tr>
				<td>수정일자</td>
				<td>${memberOne.updatedate}</td>
			</tr>
		</table>
	<a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
	<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
</body>
</html>