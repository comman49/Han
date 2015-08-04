package com.newlecture.web;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;

public class Program {

	static{
		SqlNewlecSessionFactory factory = new SqlNewlecSessionFactory();
		try {
			factory.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {		
		
		// Dependency New
		/*NoticeDao noticeDao = new MyBatisNoticeDao();
		
		NoticeConsole console = new NoticeConsole();
		console.setNoticeDao(noticeDao); // Injection
*/		
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext("com/newlecture/web/spring-context.xml");

	    NoticeConsole console = (NoticeConsole) context.getBean("console");
				
		console.print();
		
	}

}
