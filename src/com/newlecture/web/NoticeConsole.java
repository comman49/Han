package com.newlecture.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.vo.Notice;

public class NoticeConsole {
	
	NoticeDao noticeDao;	
	
	@Autowired
	@Resource(name="noticeDao1")
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public void print()
	{	
		
		List<Notice> list = noticeDao.getNotices();
		
		for(Notice n : list)
			System.out.printf("title : %s\n", n.getTitle());
	}
}
