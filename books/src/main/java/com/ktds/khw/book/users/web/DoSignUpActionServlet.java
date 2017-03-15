package com.ktds.khw.book.users.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.users.biz.UsersBiz;
import com.ktds.khw.book.users.biz.UsersBizImpl;
import com.ktds.khw.book.users.vo.UsersVO;

public class DoSignUpActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsersBiz usersBiz;
    
	public DoSignUpActionServlet() {
    	usersBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("passWord");
		String userName = request.getParameter("userName");
		
		UsersVO usersVO = new UsersVO();
		usersVO.setUserId(userId);
		usersVO.setPassword(password);
		usersVO.setUserName(userName);
		
		if ( usersBiz.insertUser(usersVO) ) {
			response.sendRedirect("/books/user/signIn");
		}
		else {
			response.sendRedirect("/books/user/signUp");
		}
		
		
	}

}
