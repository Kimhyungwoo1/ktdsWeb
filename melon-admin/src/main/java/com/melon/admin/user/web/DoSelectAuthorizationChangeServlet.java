package com.melon.admin.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserVO;

public class DoSelectAuthorizationChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	public DoSelectAuthorizationChangeServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String authorizationId = request.getParameter("authorizationId");
		
		UserVO userVO = new UserVO();
		List<UserVO> userList = (List<UserVO>) userService.getOneUser(userId);
		for (int i = 0; i >userList.size(); i++ ){
			userVO.getAuthorizationId() = authorizationId;
		}
		
		
		
	}

}
