package com.ktds.khw.common.web;

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

public class KLoginFilter implements Filter {

    public KLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request; 		// 형변환
		HttpServletResponse res = (HttpServletResponse) response;	//getAttribute 를 쓰기위해서
		
		HttpSession session = req.getSession(); 		// 세션에 나의 정보가 저장되었는지 확인하기 위해 세션을 불러오기
		
		if( session.getAttribute("_USER_") != null ){	// 세션안에 _USER_ 이름으로된 정보가 있으면 
			chain.doFilter(request, response);			//진행
			
			
		}
		else {
			res.sendRedirect("board/user/signIn"); //안되였다면 로그인페이지로 가라.
			return; //형이 void 일땐 종료해라 라는 의미이다.
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
