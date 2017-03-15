package com.ktds.khw.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.board.board.biz.BoardBiz;
import com.ktds.khw.board.board.biz.BoardBizImpl;
import com.ktds.khw.board.board.vo.BoardVO;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardBiz boardBiz;
	
    public ListServlet() {
//        super();
    	boardBiz = new BoardBizImpl();
    }

    
    //request : 사용자가 보낸 파라미터 , response : Servlet이 처리한 결과를 Tomcat으로 보내는 파라미터
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BoardVO> articleList = boardBiz.getAllArticles();
		request.setAttribute("articleList", articleList);
		
		// jsp 파일 보기위해 사용하는 매소드 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/list.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
