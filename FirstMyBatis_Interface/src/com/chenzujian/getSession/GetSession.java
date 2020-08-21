package com.chenzujian.getSession;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSession {
	public static SqlSession getSession() throws IOException {
		//加载MyBatis配饰文件
				Reader reader = Resources.getResourceAsReader("config.xml");
				//sqlSessionFactory--connection
				SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
				
				SqlSession session = sessionFactory.openSession();
				return session;
	}

}
