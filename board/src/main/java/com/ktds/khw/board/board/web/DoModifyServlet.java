package com.ktds.khw.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.board.board.biz.BoardBiz;
import com.ktds.khw.board.board.biz.BoardBizImpl;
import com.ktds.khw.board.board.vo.BoardVO;

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
		
		String boardIdString = request.getParameter("boardId");
		int boardId = 0;
		
		try {
			boardId = Integer.parseInt(boardIdString);
		}
		catch (NumberFormatException e){
			throw new RuntimeException("잘못된 경로입니다.");
			
		}
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardId(boardId);
		boardVO.setWriter(request.getParameter("writer"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		
		if (boardBiz.updateArticles(boardVO)) {
			response.sendRedirect("/board/list");
		}
		else {
			response.sendRedirect("/board/modify");
		}
		
	}

}
