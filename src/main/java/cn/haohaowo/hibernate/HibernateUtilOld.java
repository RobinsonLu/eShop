package cn.haohaowo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtilOld {

	private static final SessionFactory sessionfactory = 
		new AnnotationConfiguration().configure().buildSessionFactory();
	private static final ThreadLocal<Session> threadlocal = 
		new ThreadLocal<Session>();
	
	public static Session getSession(){
	
		Session session = threadlocal.get();
		if(null != session){
			session = sessionfactory.openSession();
			threadlocal.set(session);
		}
		return session;
	}
	
	public static void close(){
		Session session = getSession();
		if(null != session && session.isOpen()){
			session.close();
		}
		threadlocal.set(null);
	}
}
