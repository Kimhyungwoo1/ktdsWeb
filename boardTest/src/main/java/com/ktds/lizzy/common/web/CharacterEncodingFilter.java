package com.ktds.lizzy.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 이렇게 설정해두면 response와 request에 하나하나 인코딩 설정할 필요 X
		 */
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response); // Servlet에 요청하기 or 응답하기
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
