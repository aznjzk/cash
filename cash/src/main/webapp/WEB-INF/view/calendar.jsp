<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSP컴파일 시 자바코드로 변환되는 c : ...(제어문법코드) 커스텀태그 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- jstl substring호출 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar</title>
</head>
<body>
	<!-- 변수값or반환값 : EL사용 $ 표현식 -->
	<!-- 
	   속성값대신 EL사용 
	   ex) 
	   request.getAttribute("targetYear") -- requestScope.targetYear 
	   (requestScope는 생략가능)
	   형변환연산이 필요없다(EL이 자동으로 처리)
	-->

	<!-- 자바코드(제어문) : JSTL 사용 -->
	<h1>${targetYear}년 ${targetMonth+1}월 달력</h1>
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
	
	<table border="1" width="80%">
		<tr>
			<th>일</th>
			<th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
				<c:set var="d" value="${i-beginBlank+1}"></c:set>
			
				<c:if test="${i!=0 && i%7==0}">
					<tr></tr>
				</c:if>
				
				<c:if test="${d < 1 || d > lastDate}">
					<td></td>
				</c:if>
				
				<c:if test="${!(d < 1 || d > lastDate)}">
					<td onclick="location.href='${pageContext.request.contextPath}/cashbookOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}'">
					<div>${d}</div>
						<c:forEach var="c" items="${list}">
							<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
								<div>
									<c:if test="${c.category == '수입'}">
										<span style="color:blue;">+${c.price}</span>
									</c:if>
									<c:if test="${c.category == '지출'}">
										<span style="color:red;">-${c.price}</span>
									</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>