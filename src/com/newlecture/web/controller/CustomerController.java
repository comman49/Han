package com.newlecture.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;

@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	/*모든 메서드에서 사용하므로 필드로 뺐다.*/
	private NoticeDao noticeDao;
	private NoticeFileDao noticeFileDao;
	
	
	/*context에서 관련된 dependency를 찾아 이어준다.*/
	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	@Autowired
	public void setNoticeFileDao(NoticeFileDao noticeFileDao) {
		this.noticeFileDao = noticeFileDao;
	}



	@RequestMapping("notice")
	public String notice(Model model){
		//NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();

		model.addAttribute("list", list);
		
		return "customer.notice";
	}
	
	@RequestMapping("noticeDetail")
	public String noticeDetail(String c, Model model){
		//NoticeDao noticeDao = new MyBatisNoticeDao();
		Notice n = noticeDao.getNotice(c);

		model.addAttribute("n", n);
		
		
		/*return "/WEB-INF/view/customer/noticeDetail.jsp";*/
		//타일을 사용할 경우 아래처럼 사용함
		return "customer.noticeDetail";
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg(){
		
		/*return "/WEB-INF/view/customer/noticeReg.jsp";*/
		return "customer.noticeReg";
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST)
	public String noticeReg(Notice n, MultipartFile file, Principal principal, 
			HttpServletRequest request, SecurityContext securityContext/*, SecurityContextHolder holder*/) throws IOException{
		
		
		
		/*아래처럼 할 경우 역할에 맞는 구문을 만들 수 있다.*/
		/*아래 구문은 역할이 admin일 경우 실행되는 if문이다.*/
		if (request.isUserInRole("ROLE_ADMIN")) {
			
		}
		
		/*아래는 역할이 무엇이 있는지 출력하는 문장*/
		/*SecurityContextHolder로도 확인 가능하다*/
		/*for문을 사용해 list를 돌며 출력 가능*/
		securityContext.getAuthentication().getAuthorities();
//		holder.getContext();
		
		
		/*아래는 로그인 여부를 확인할 수 있는 문장*/
		securityContext.getAuthentication().isAuthenticated();

		
		
		/*원래는 request를 사용하여 이름을 얻어내야하나 스프링을 이용하면
		princpal에서 직접 얻어 올 수 있다.*/
		n.setWriter(principal.getName());
		noticeDao.addNotice(n);
		String lastCode = noticeDao.getLastCode();
		
		if(!file.isEmpty())
		{
			//Part part = request.getPart("file");
			ServletContext application = request.getServletContext();
						
			String url = "/resource/customer/upload";
			String path = application.getRealPath(url);
			String temp = file.getOriginalFilename();//part.getSubmittedFileName();
			String fname = temp.substring(temp.lastIndexOf("\\")+1);
			String fpath = path + "\\" + fname;
			
			InputStream ins = file.getInputStream();//part.getInputStream();
			OutputStream outs = new FileOutputStream(fpath);
			
			byte[] 대야 = new byte[1024];
			int len = 0;
			
			while((len = ins.read(대야, 0, 1024)) >= 0)
				outs.write(대야, 0, len);
			
			outs.flush();
			outs.close();
			ins.close();
			
			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setNoticeCode(lastCode);
			noticeFile.setName(fname);
			noticeFileDao.addNoticeFile(noticeFile);
		}
				
		return "redirect:notice";
	}
}
