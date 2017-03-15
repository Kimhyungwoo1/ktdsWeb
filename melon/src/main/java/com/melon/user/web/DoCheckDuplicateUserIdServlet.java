package com.melon.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;

public class DoCheckDuplicateUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
    public DoCheckDuplicateUserIdServlet() {
    	userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//아이디 중복확인을 자동으로 실행해주는 servlet
		
		String userId = request.getParameter("userId");
		boolean isDuplicate = userService.isDuplicateUserId(userId);
		
		StringBuffer json = new StringBuffer();
		json.append(" { ");
		json.append(" \"status\" : \"success\", ");
		json.append(" \"duplicated\" :" + isDuplicate);
		json.append(" } ");
		
		PrintWriter writer = response.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();
		
	}

}
