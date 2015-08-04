package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.vo.Notice;

public interface NoticeDao {
	public List<Notice> getNotices(int page, String field, String query);
	public List<Notice> getNotices(int page);
	public List<Notice> getNotices();
	public Notice getNotice(String code);
	public int addNotice(Notice notice);
	public String getLastCode();
}
