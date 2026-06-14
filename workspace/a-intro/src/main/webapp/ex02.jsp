<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX02</title>
</head>
<body>
	<form action="ex02" method="post">	<!-- 여기 온게 GET 방식, 문제의 요구인 연산자체는 간단하지만, 실무에서 DB를 통한다고 가정하고 post방식을 채택하자. -->
		<h3>정수 2개를 입력해보자</h3>
		<input type="number" name="number01" placeholder="첫번째 정수"><br> <!-- type="number"로 하나 "text"로 하나 어차피 Integer.parseInt해줘야함 -->
		<input type="number" name="number02" placeholder="두번째 정수">
		<button>전송</button>
	</form>
</body>
</html>