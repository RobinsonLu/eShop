package cn.haohaowo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.haohaowo.common.Constants;
import cn.haohaowo.entity.Account;
import cn.haohaowo.service.AccountService;
import cn.haohaowo.service.impl.AccountServiceImpl;


public class LoginFromCookieFilter extends AbstractFilter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (!loginFromSession(req)) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (Constants.REMEMBER_ME_KEY.equals(cookie.getName())) {
						String[] value = cookie.getValue().split(Constants.REMEMBER_ME_SEPERATOR);
						String username = value[0];
						String password = value[1];
						AccountService accountService = new AccountServiceImpl();
						Account account = accountService.login(username, password);
						if (account != null) {
							getSessionContainer(req).setAccount(account);
							break;
						}
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}
}
