package com.melon.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
    public SignInServlet() {
    	userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setUserPassword(userPassword);
		
		System.out.println(userId);
		System.out.println(userPassword);
		
		UserVO loginUserVO = new UserVO();
		loginUserVO = userService.loginUser(userVO);
		
		if ( loginUserVO != null ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("_USER_", loginUserVO);
			response.sendRedirect("/melon/artist/list");
		}
		else {
			response.sendRedirect("/melon/user/signUp");
		}
		
	}

}
