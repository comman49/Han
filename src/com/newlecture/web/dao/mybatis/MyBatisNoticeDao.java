package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;
import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;

public class MyBatisNoticeDao implements NoticeDao{
	/*스프링은 오토커밋*/
	
	
	/*스프링을 사용하는 형태.
	단지, 선언만 해주면 Autowired에 의해서 context에 있는 내용을 가져와 연결한다.*/
	@Autowired
	private SqlSession session;
	
	
	/*DI가 가능한 약한 결합력 형태
	그러나 스프링을 사용하는 것은 아니다*/
	/*@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}*/
	
	/*DI를 못하는 강한 결합력을 가진 형태*/
	//SqlSessionFactory factory = 
			//new SqlNewlecSessionFactory().getSqlSessionFactory();
	
	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		
		//SqlSession session = factory.openSession();		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		NoticeFileDao fileDao = session.getMapper(NoticeFileDao.class);
		
		List<Notice> list = dao.getNotices(page, field, query);
		
		for(Notice n : list)		
			n.setFiles(fileDao.getNoticeFilesOfNotice(n.getCode()));		
				
		return list;
	}

	@Override
	public Notice getNotice(String code) {
		//SqlSession session = factory.openSession();		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		
		return dao.getNotice(code);
	}

	@Override
	public List<Notice> getNotices(int page) {
		
		return getNotices(page, "TITLE", "");
	}

	@Override
	public List<Notice> getNotices() {
		// TODO Auto-generated method stub
		return  getNotices(1, "TITLE", "");
	}

	@Override
	public int addNotice(Notice notice) {
		
		int result = 0;
		//SqlSession session = factory.openSession();//true AutoCommit
		
		try{
			NoticeDao dao = session.getMapper(NoticeDao.class);
			result = dao.addNotice(notice);
//			session.commit();
		}
		finally{
//			session.rollback();
//			session.close();
		}
		
		return result;
	}

	@Override
	public String getLastCode() {
		//SqlSession session = factory.openSession();		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		
		return dao.getLastCode();
	}

}
