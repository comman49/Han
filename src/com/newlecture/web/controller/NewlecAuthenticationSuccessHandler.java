package com.newlecture.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.vo.Member;

public class NewlecAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	/*service-context에 있는 bean을 가져다가 자동으로 이어준다.*/
	@Autowired
	private MemberDao memberDao;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		
		/*DefaultRole이 왜 필요한가??*/
		
		
		
		/*유저 ID를 얻어내는 작업*/
		String uid = authentication.getName();
		Member m = memberDao.getMember(uid);
		String type = m.getDefaultRole();
		
		/*타입에 따라서 targetUrl이 달라져야한다.*/
		String targetUrl = "/customer/notice";
		
		/*롤이 ROLE_TEACHER일 경우 바뀐 targetUrl로 이동*/
		if (type.equals("ROLE_TEACHER")) {
			targetUrl="/customer/noticeDetail?c=5";
		}
		
		/*targetUrl로 페이지 이동을 위한 작업*/
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}

}
