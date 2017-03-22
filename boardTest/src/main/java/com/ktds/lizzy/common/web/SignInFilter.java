package com.ktds.lizzy.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("_USER_") != null) {
			chain.doFilter(req, resp);
		}
		else {
			resp.sendRedirect("/boardTest/user/signIn");
			return; // void일 때 return의 의미 : "종료시켜라" 
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
