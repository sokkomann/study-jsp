package com.app.ex;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ex04Result extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String region = req.getParameter("region");
		String result = req.getParameter("result");
		
		req.setAttribute("region", region);
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("/ex04-result.jsp").forward(req, resp);
	}
}
