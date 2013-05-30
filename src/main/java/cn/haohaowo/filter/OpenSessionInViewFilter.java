package cn.haohaowo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;

import cn.haohaowo.hibernate.HibernateUitl;

public class OpenSessionInViewFilter extends AbstractFilter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			HibernateUitl.getSession().beginTransaction();
			chain.doFilter(request, response);
			HibernateUitl.getSession().getTransaction().commit();
		}catch(HibernateException e){
			if(HibernateUitl.getSession().getTransaction().isActive()){
				HibernateUitl.getSession().getTransaction().rollback();
			}
			e.printStackTrace();
		}finally{
			HibernateUitl.close();
		}
	}

}
