package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MyPage")
public class MyPage extends HttpServlet {		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		int a = 0;
		int s = 0;
		int c = 0;
		
		Object _a = application.getAttribute("a");
		Object _s = session.getAttribute("s");
		
		Cookie[] cookies = request.getCookies();
		String _c = null;

		if (cookies != null)		
		    for (Cookie cookie : cookies)
		        if("c".equals(cookie.getName()))
		            _c = cookie.getValue();		
		
		if(_a != null)
			a = (int) _a;
		
		if(_s != null)
			s = (int) _s;
		
		if(_c != null)
			c = Integer.parseInt(_c);
		
		out.printf("Application : %d<br/>", a);
		out.printf("Session : %d<br/>", s);
		out.printf("Cookie : %d<br/>", c);
		out.println("<a href=\"add\">계산하기</a>");
	}
}
