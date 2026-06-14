<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX04</title>
</head>
<body>
	<form action="ex04" method="post" name="form">	
		<h3>지역 입력(서울, 경기도만)</h3>
		<input type="text" name="region" placeholder="서울 혹은 경기도 입력">
		<input type="button" value="전송">
	</form>	
</body>
<script>
	const button = document.querySelector("input[type=button]");
	const region = form.region;
	const tempBorder = region.style.border;
	
	region.addEventListener("blur", (e) => {
		const value = e.target.value;
		if(!value) {
			e.target.style.border = "1px solid red";
			return;
		}
		e.target.style.border = tempBorder;	
	});
	
	button.addEventListener("click", (e) => {
		const value = region.value;
		
		if(!region.value) {
	        region.style.border = "1px solid red";
	        alert("값을 입력해주세요.");
	        region.focus();
	        return;
	    }
		
		if(value !== "서울" && value !== "경기도") {
			region.style.border = "1px solid red";
			alert("서울 혹은 경기도만 입력 가능합니다.");
			region.focus();
			return;
		} 
		region.style.border = tempBorder;
		form.submit();
	});
</script>
</html>