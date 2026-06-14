package com.app.member.controller;

import java.io.IOException;

import com.app.Action;
import com.app.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinController implements Action{
	
//	컨트롤러마다 이 메소드는 같으니까 인터페이스의 추상메소드로 선언해놓자
//	이 메소드에서 벌어진 결과를 프론트컨트롤러가 알아야하니 리턴이 있어야함.
//	프론트 컨트롤러가 궁금해 하는것 1. 포워드냐 리다이렉트냐 2. 어디로가냐
//	근데 리턴값 2개는 안되잖아.
//	=> 클래스 두개를 만들자
//	그래서 클래스자료형 메소드
	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result();
		
		result.setPath("/member/join.jsp");
		
		return result;
	}
}














