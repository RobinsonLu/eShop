package cn.haohaowo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter extends AbstractFilter 
{
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

//	public void init(FilterConfig cofig) throws ServletException 
//	{
//		String encoding = cofig.getInitParameter("encoding");
//		if(null != encoding || "" != encoding) {
//		}
//	}

}
