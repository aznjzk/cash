<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookOne.jsp</title>
</head>
<body>
<h1>수입/지출 상세 정보</h1>
<a href="${pageContext.request.contextPath}/calendar">이전으로</a>
<a href="${pageContext.request.contextPath}/addCashbook?cashbookDate=${cashbookDate}">가계부 추가</a>
	<table border="1">
		<tr>
			<th>날짜</th>
			<th>금액</th>
			<th>메모</th>
			<th>수정일</th>
		</tr>
		<c:forEach var="c" items="${list}">
			<tr>
         <td>${c.cashbookDate}</td>
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
</body>
</html>