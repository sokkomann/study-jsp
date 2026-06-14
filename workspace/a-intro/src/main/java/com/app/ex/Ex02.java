package com.app.ex;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ex02 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/ex02.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String value01 = req.getParameter("number01");
		String value02 = req.getParameter("number02");
		
		int num01 = Integer.parseInt(value01);
		int num02 = Integer.parseInt(value02);
		
		int result = num01 + num02;
		
		// redirect 를 하는 이유는 -> 이전경로를 기억하면 안됨. 
		resp.sendRedirect(req.getContextPath() + "/ex02-result?num01=" + num01 +"&num02=" + num02 + "&result=" + result);
	}

}
