<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%--
 	jsp 주석
 	- 단순출력 EL
 	- 분기/반복처리 JSTL
 	- 자바코드 작성 scriptlet
 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="uft-8"/>
	<title>개인취향검사 결과 POST</title>
	<body>
		<h1>개인취향검사 결과 POST</h1>
		<p>반갑습니다. ${param.name}님</p>
		<p>${param.color}색을 좋아하십니다.</p>
		<p>동물은 ${param.animal}를 좋아하십니다.</p>
		<p>중국집 음식중에는
		<%--
			items 반복접근할 객체 (배열, List, Set, Map) - EL로 작성할 것
			var 반목문 안에서 요소를 담을 변수명
		 --%>
		<c:forEach items = "${paramValues.food}" var="food">
			 ${food}
		</c:forEach>
		을 좋아하십니다.
		</p>
		
		<%-- 서버에서 생성한 데이터 --%>
		<h2>오늘은 ${recommendation} 어떠십니까?</h2>
	</body>
</head>
</htm>