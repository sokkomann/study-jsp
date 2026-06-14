package com.app.ex;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class Ex04 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/ex04.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String region = req.getParameter("region");
		String result = null;
		
		if(region.equals("서울")) {
			result = "강남구";
		} else {
			result = "남양주";
		}
		
		resp.sendRedirect(req.getContextPath() + "/ex04-result?region=" + URLEncoder.encode(region, "UTF-8") + "&result=" + URLEncoder.encode(result, "UTF-8"));
	}
}
