package com.ktds.lizzy.board.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.lizzy.board.user.biz.UserBiz;
import com.ktds.lizzy.board.user.biz.UserBizImpl;
import com.ktds.lizzy.board.user.vo.UserVO;

public class DoSignInActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserBiz userBiz;

	public DoSignInActionServlet() {
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UserVO user = new UserVO();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		
		user = userBiz.checkUser(userId, userPassword);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("_USER_", user); // Session Container에 정보를 집어넣음 (Key/Value)
												  // 통상적으로 UnderBar('_')를 붙여 Session의 Key임을 알려준다.
			
			response.sendRedirect("/boardTest/board");
		}
		else {
			response.sendRedirect("/boardTest/user/signIn");
		}
	}

}
