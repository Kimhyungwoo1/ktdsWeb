package com.ktds.lizzy.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.lizzy.board.user.vo.UserVO;
import com.ktds.lizzy.common.web.AuthConst;


public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WriteServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
