package cn.haohaowo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUitl {

	private static final SessionFactory sessionfactory;
	static{
		try{
			sessionfactory = new AnnotationConfiguration().
			configure().buildSessionFactory();
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
		
	}
		
	
	public static Session getSession(){
		return sessionfactory.getCurrentSession();
	}
	public static void close(){
		getSession().close();
	}
}
