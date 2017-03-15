package com.ktds.khw.book.users.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.khw.book.users.biz.UsersBiz;
import com.ktds.khw.book.users.biz.UsersBizImpl;
import com.ktds.khw.book.users.vo.UsersVO;

public class DoSignInActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsersBiz usersBiz;
	
    public DoSignInActionServlet() {
    	usersBiz = new UsersBizImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String userId = request.getParameter("userId");
    	String passWord = request.getParameter("passWord");
    	
    	UsersVO usersVO = new UsersVO();
    	usersVO.setUserId(userId);
    	usersVO.setPassword(passWord);
    	
    	usersVO = usersBiz.selectUser(usersVO);
    	HttpSession session = request.getSession();
    	session.setAttribute("_USER_", usersVO);
    	
    	if ( usersVO == null ) {
    		response.sendRedirect("/books/user/signUp");
    	}
    	else {
    		response.sendRedirect("/books/list");
    	}
    	
	}

}
