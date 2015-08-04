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
	/*service-context�� �ִ� bean�� �����ٰ� �ڵ����� �̾��ش�.*/
	@Autowired
	private MemberDao memberDao;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		
		/*DefaultRole�� �� �ʿ��Ѱ�??*/
		
		
		
		/*���� ID�� ���� �۾�*/
		String uid = authentication.getName();
		Member m = memberDao.getMember(uid);
		String type = m.getDefaultRole();
		
		/*Ÿ�Կ� ���� targetUrl�� �޶������Ѵ�.*/
		String targetUrl = "/customer/notice";
		
		/*���� ROLE_TEACHER�� ��� �ٲ� targetUrl�� �̵�*/
		if (type.equals("ROLE_TEACHER")) {
			targetUrl="/customer/noticeDetail?c=5";
		}
		
		/*targetUrl�� ������ �̵��� ���� �۾�*/
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}

}
