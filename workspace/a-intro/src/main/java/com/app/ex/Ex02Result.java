package com.app.ex;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ex02Result extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String number1 = req.getParameter("num01");
		String number2 = req.getParameter("num02");
		String result = req.getParameter("result");
//		req.setAttribute("result", result);
		req.getRequestDispatcher("/ex02-result.jsp?number1=" + number1 +"&number2=" + number2 + "&result=" + result).forward(req, resp);
	}
}
