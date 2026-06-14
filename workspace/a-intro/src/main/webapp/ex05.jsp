<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX05</title>
<style>
   p {
      color: red;
   }
</style>
</head>
<body>
   <form action="ex05" method="post">
      <%if(request.getParameter("login") != null) {%>
         <p>아이디 혹은 비밀번호를 다시 확인해주세요!</p>
      <%} %>
      <input type="text" name="memberId" placeholder="아이디">
      <input type="password" name="memberPassword" placeholder="비밀번호">
      <button>로그인</button>
   </form>
</body>
</html>













