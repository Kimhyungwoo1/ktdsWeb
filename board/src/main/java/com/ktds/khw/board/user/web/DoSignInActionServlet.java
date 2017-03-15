package com.ktds.khw.board.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.khw.board.user.biz.UserBiz;
import com.ktds.khw.board.user.biz.UserBizImpl;
import com.ktds.khw.board.user.vo.UserVO;

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
		
		UserVO userVO = new UserVO();
		userVO.setUserId(request.getParameter("userId"));
		userVO.setUserPassword(request.getParameter("userPassWord"));
		
		userVO = userBiz.printSignIn(userVO);
		
		if ( userVO == null ) {
			response.sendRedirect("/board/user/login");
		}
		else {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("_USER_", userVO);		//언더바를 붙히는 이유는 세션의 키인지 아닌지를 구분해준다.
			
			System.out.println("User ID :" + userVO.getUserId());
			System.out.println("User Name :" + userVO.getUserName());
			System.out.println("User PassWord :" + userVO.getUserPassword());
			System.out.println("User Date :" + userVO.getJoinDate());
			response.sendRedirect("/board/list");
		}
		
		
	}

}
