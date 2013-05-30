package cn.haohaowo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.haohaowo.common.Constants;


public class AuthenticationFilter extends AbstractFilter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (loginFromSession(req)) {
			chain.doFilter(req, resp);
		} else {
			storeReturnPath(req);
			req.getSession().setAttribute(Constants.LOGIN_MSG_KEY, "ÇëµÇÂ¼");
			
			resp.sendRedirect(req.getContextPath() + "/accounts/login.action");
		}
	}
}
