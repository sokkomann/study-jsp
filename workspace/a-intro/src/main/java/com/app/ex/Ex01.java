package com.app.ex;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ex01 extends HttpServlet{
//  서블릿이네 -> 이제 요청 응답 되겠다.
//	private static final long serialVersionUID = 1L; -> 지금은 그냥 이런게 있다~
       
//    public Ex01() {  기본생성자 필요없음 new 할것도아닌데
//        super();
//    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		내가이걸왜만들었지-ex01로이동하려고
		req.getRequestDispatcher("/ex01.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		받을준비
		StringBuilder stringBuilder = new StringBuilder();
		String memberName = req.getParameter("memberName");
//		request.getParameter("input의 name이름");
		
		stringBuilder.append(memberName);
		stringBuilder.append("님");
		
		memberName = stringBuilder.toString();

		resp.sendRedirect(req.getContextPath() + "/ex01-result?memberName=" + URLEncoder.encode(memberName, "UTF-8"));
	}
}
