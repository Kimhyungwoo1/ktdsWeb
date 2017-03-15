package com.melon.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
    public SignUpServlet() {
    	userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		
		if ( userId == null || userId.length() == 0 ) {
			response.sendRedirect("/melon/user/signUp?errorCode=0");
		}
		if ( userPassword == null || userPassword.length() == 0){
			response.sendRedirect("/melon/user/signUp?errorCode=1");
		}
		if ( userName == null || userName.length() == 0){
			response.sendRedirect("/melon/user/signUp?errorCode=2");
		}
		if ( userService.isDuplicateUserId(userId)){
			response.sendRedirect("/melon/user/signUp?errorCode=3");
		}
		
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setUserName(userName);
		userVO.setUserPassword(userPassword);
		
		if ( userService.registNewUser(userVO) ) {
			StringBuffer script = new StringBuffer();
			script.append("<script type='text/javascript'>");
			script.append("      opener.location.reload(); ");
			script.append("      self.close(); ");
			script.append("</script>");
			
			PrintWriter writer = response.getWriter();
			writer.write(script.toString());
			writer.flush();
			writer.close();
		}
		else{
			response.sendRedirect("/melon/user/signUp");
		}
		
	}

}
