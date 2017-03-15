package com.ktds.khw.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.board.board.biz.BoardBiz;
import com.ktds.khw.board.board.biz.BoardBizImpl;
import com.ktds.khw.board.board.vo.BoardVO;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public ViewModifyServlet() {

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
			throw new RuntimeException("잘못된 접근입니다.");
			
		}
		
		BoardVO boardVO = boardBiz.getArticles(boardId);
		
		String content = boardVO.getContent();
		
		content = content.replaceAll("<br/>", "\n");
		boardVO.setContent(content);
		
		request.setAttribute("board", boardVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/modify.jsp");
		dispatcher.forward(request, response);
		
	}

}
