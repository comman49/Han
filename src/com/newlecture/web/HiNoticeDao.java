package com.newlecture.web;

import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.vo.Notice;

public class HiNoticeDao implements NoticeDao {

	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getNotices(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getNotices() {
		// TODO Auto-generated method stub
		List<Notice> list = new ArrayList<Notice>();
		Notice n = new Notice();
		n.setTitle("°¡¶ó");
		list.add(n);
		
		return list;
	}

	@Override
	public Notice getNotice(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLastCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
