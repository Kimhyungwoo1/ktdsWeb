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

public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;
	
	public ViewDetailServlet() {
		
		boardBiz = new BoardBizImpl();
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardIdString = request.getParameter("boardId"); 
		
		int boardId = 0;
		try{
			boardId = Integer.parseInt(boardIdString); // 형변환을 하는이유는 boardId 의 정보가 숫자인데 request.getParameter("boardId"); 가 스트링으로 받기 때문에 형변환 해준거다.
		}
		catch (NumberFormatException e) {
			//throw new RuntimeException("잘못된 접근입니다.");
			throw new RuntimeException("존재하지 않는 게시물 입니다.");
		}
		BoardVO board = boardBiz.getArticles(boardId);
		
		request.setAttribute("board", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/detail.jsp");
		dispatcher.forward(request, response);
		
	}

}
