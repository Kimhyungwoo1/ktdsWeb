package com.ktds.lizzy.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.board.user.vo.UserVO;

public class DoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;

    public DoModifyServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		
		int boardId = 0;
		try {
			boardId = Integer.parseInt(request.getParameter("boardId"));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("�߸��� �����Դϴ�.");
		}
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");

		
		BoardVO boardVO = new BoardVO();
		
		boardVO.setBoardId(boardId);
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContents(contents);
		
		if (boardBiz.modifyArticle(boardVO)) {
			response.sendRedirect("/boardTest/board");
		}
		else {
			response.sendRedirect("/boardTest/modify");
		}
	}

}
