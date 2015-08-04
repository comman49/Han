package com.newlecture.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlNewlecSessionFactory extends HttpServlet {
	public static SqlSessionFactory ssf;
	
	@Override
	public void init() throws ServletException {
		String resource = "com/newlecture/web/dao/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		
		try {
            inputStream = Resources.getResourceAsStream(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ssf = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	public SqlSessionFactory getSqlSessionFactory(){			
        
		return ssf;
	}
}






