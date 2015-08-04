package com.newlecture.web.controller.joinus;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.mybatis.MyBatisMemberDao;
import com.newlecture.web.vo.Member;

/*@WebServlet("/joinus/login")*/
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getMethod().equals("POST"))
		{
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			
			MemberDao memberDao = new MyBatisMemberDao();
			Member member = memberDao.getMember(uid);
			
			if(member == null)
			{
				//아이디 오류 메시지 보내기
				request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			}
			else if(!member.getPwd().equals(pwd))
			{
				// 비번 오류 메시지
				request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			}
			else
			{
				// 인증 성공
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				
				response.sendRedirect("../customer/notice");
			}
			
		}
		else
		{				
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
