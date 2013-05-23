package cn.haohaowo.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cn.haohaowo.common.Constants;
import cn.haohaowo.common.SessionContainer;
import cn.haohaowo.entity.Account;
import cn.haohaowo.util.StringUtils;

public abstract class AbstractFilter implements Filter {

	public void destroy() {}

	public void init(FilterConfig arg0) throws ServletException {}
	
	protected boolean loginFromSession(HttpServletRequest request){
		return null != this.getCurrentAccount(request);
	}
	
	protected Account getCurrentAccount(HttpServletRequest request){
		return (Account)this.getSessionContainer(request).getAccount();
	}
	
	protected SessionContainer getSessionContainer(HttpServletRequest request){
		SessionContainer container = (SessionContainer) request.getSession()
		.getAttribute(Constants.SESSION_CONTAINER_KEY);
		
		if(null == container){
			container = new SessionContainer();
			request.getSession().setAttribute(Constants.SESSION_CONTAINER_KEY, container);
		}
		
		return container;
	}

	protected void storeReturnPath(HttpServletRequest request){
		String returnTo = request.getRequestURI();
		String queryString = request.getQueryString();
		
		if(StringUtils.isNotEmpty(queryString)){
			returnTo += "?" + queryString;
		}
		request.getSession().setAttribute(Constants.RETURN_PATH, returnTo);
	}
}
