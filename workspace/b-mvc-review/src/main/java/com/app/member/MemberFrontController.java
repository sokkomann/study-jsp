package com.app.member;

import java.io.IOException;

import com.app.Result;
import com.app.member.controller.JoinController;
import com.app.member.controller.JoinOkController;
import com.app.member.controller.LoginController;
import com.app.member.controller.LoginOkController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String target = uri.substring(0, uri.lastIndexOf(".")).replace(req.getContextPath(), "");
		Result result = null;
//		http://localhost:9000/a-intro/join.member 에서
//		19번으로 /a-intro/join.member 가 되고
//		20번의 substring(0, uri.lastIndexOf(".")) 으로 /a-intro/join 이 되고 
//		replace(req.getContextPath(), "")로 "/join" 만 남음  ContextPath 가 /a-intro 임

		if (target.equals("/join")) {
//			join(회원가입)에 맞는 코드 실행. 근데 너무 길잖아 -> 컨트롤러 만들어
//			join 페이지로 이동하는 컨트롤러를 만들자 -> 컨트롤러에서 DAO를 써.
//			컨트롤러에 있는 메소드를 프론트컨트롤러에서 쓰는거야
			result = new JoinController().execute(req, resp);
//			if (result != null) {
//				if (result.isRedirect()) {
//					resp.sendRedirect(result.getPath());
//				} else {
//					req.getRequestDispatcher(result.getPath()).forward(req, resp);
//				}
//			}		=> 이런식으로 하면 개별처리.(밑에 각 else if 에도 다 붙임)
		} else if (target.equals("/join-ok")) {
			result = new JoinOkController().execute(req, resp);
		} else if (target.equals("/login")) {
			result = new LoginController().execute(req, resp);
		} else if (target.equals("/login-ok")) {
			result = new LoginOkController().execute(req, resp);
		}

		if (result != null) {
			if (result.isRedirect()) {
				resp.sendRedirect(result.getPath());
			} else {
				req.getRequestDispatcher(result.getPath()).forward(req, resp);
			}
		}		// 이렇게하면 일괄처리

	}

//	사용자가 어떤 방식으로 요청할지 몰라. 그니까 하나만 개발해놓고 나머지도 실행될수 있게끔 이렇게함
//	=> 포스트로 와봤자 doGet메소드 하니까 저거만 코딩
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
