package com.app.ex;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ex05 extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.getRequestDispatcher("/ex05.jsp").forward(req, resp);
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String memberId = req.getParameter("memberId");
      String memberPassword = req.getParameter("memberPassword");
      
      String path = memberId.equals("test") && memberPassword.equals("1234") ? "/ex05-result?memberId=" + memberId : "/ex05?login=false";
      
      resp.sendRedirect(req.getContextPath() + path);
   }
}



















