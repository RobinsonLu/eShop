package cn.haohaowo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.haohaowo.common.Constants;
import cn.haohaowo.entity.Account;
import cn.haohaowo.service.AccountService;
import cn.haohaowo.service.impl.AccountServiceImpl;


public class LoginFilter extends AbstractFilter
{
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if(loginFromSession(request) || loginFromCookie(request))
		{
			chain.doFilter(req, res);
		}
		else
		{
			this.storeReturnPath(request);
			request.getSession().setAttribute(Constants.LOGIN_MSG_KEY, "ÇëµÇÂ¼");
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
	}
	
	public boolean pathContains(String path)
	{
		return path.contains("order") || path.contains("my") || 
		path.contains("modifyuserinfo");
	}
	
	
	private boolean loginFromCookie(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().equals(Constants.REMEMBER_ME_KEY))
				{
					String value = cookie.getValue();
					String[] values = value.split(Constants.REMEMBER_ME_SEPERATOR);
					String username = values[0];
					String password = values[1];
					AccountService customerservice = new AccountServiceImpl();
					Account account = customerservice.login(username, password);
					if(null != account)
					{
						this.getSessionContainer(request).setAccount(account);
						return true;
					}
				}
				break;
			}
		}
		return false;
	}
}
