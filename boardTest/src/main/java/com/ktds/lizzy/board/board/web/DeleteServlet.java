package com.ktds.lizzy.board.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
 
    public DeleteServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardId;
		try {
			boardId = Integer.parseInt(request.getParameter("boardId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("�߸��� �����Դϴ�.");
		}
		
		if (boardBiz.deleteArticle(boardId)) {
			response.sendRedirect("/boardTest/board");
		}
		else {
			response.sendRedirect("/boardTest/detail");
		}
	}

}
