package com.ktds.lizzy.board.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.user.biz.UserBiz;
import com.ktds.lizzy.board.user.biz.UserBizImpl;
import com.ktds.lizzy.board.user.vo.UserVO;

public class DoSignUpActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserBiz userBiz;

	public DoSignUpActionServlet() {
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO userVO = new UserVO();
		
		userVO.setUserId(request.getParameter("userId"));
		userVO.setUserName(request.getParameter("userName"));
		userVO.setUserPassword(request.getParameter("userPassword"));
		
		if (userBiz.signUpUser(userVO)) {
			response.sendRedirect("/boardTest/user/signIn");
		}
		else {
			response.sendRedirect("/boardTest/user/signUp");
		}
	}

}
