<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookOne</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h1>
		<a href="${pageContext.request.contextPath}/calendar" class="btn btn-dark btn-block">이전으로</a>
		<a href="${pageContext.request.contextPath}/addCashbook?cashbookDate=${cashbookDate}" class="btn btn-dark btn-block">가계부 추가</a>
		<table class="table table-bordered">
		<tr>
			<th class="table-dark">금액</th>
			<th class="table-dark">메모</th>
			<th class="table-dark">수정</th>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
				<c:if test="${c.category == '수입'}">
					<td style="color:blue;">+${c.price}</td>
				</c:if>
				<c:if test="${c.category == '지출'}">
					<td style="color:red;">-${c.price}</td>
				</c:if>
				<td>${c.memo}</td>
				<td>${c.updatedate}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>