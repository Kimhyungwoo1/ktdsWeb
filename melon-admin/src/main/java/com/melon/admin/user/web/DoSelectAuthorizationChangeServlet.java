package com.melon.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;

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
		
		String authBefore = request.getParameter("authBefore");
		String authAfter = request.getParameter("authAfter");
		String[] checkAuth = request.getParameterValues("authCheck");		// 체크박스의 value 값을 가져오는 것
		
		System.out.println(checkAuth[0]);
		System.out.println(checkAuth[1]);
		
		boolean userCheckUpdate = userService.updateUserAuthorization(checkAuth, authAfter, authBefore);
		
		boolean userUpdates = userService.updateAllUser(authBefore, authAfter);

		if (Arrays.toString(checkAuth).equals("{}")){
			if ( userUpdates ) {
				response.sendRedirect("/melon-admin/user/list");
			}
		}
		else if (checkAuth != null){
			if( userCheckUpdate ){
				response.sendRedirect("/melon-admin/user/list");
			}
		}
			
		
		
		

	}

}
