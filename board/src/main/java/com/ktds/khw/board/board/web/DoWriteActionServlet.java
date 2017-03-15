package com.ktds.khw.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.khw.board.board.biz.BoardBiz;
import com.ktds.khw.board.board.biz.BoardBizImpl;
import com.ktds.khw.board.board.vo.BoardVO;
import com.ktds.khw.board.user.vo.UserVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardBiz boardBiz;
	
    public DoWriteActionServlet() {
    	
    	boardBiz = new BoardBizImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_"); // 세션에서 내정보만 가져오는 소스
		
		String writer = userVO.getUserName();
		
//		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		content = content.replaceAll("\n","<br/>");
		content = content.replaceAll("\r","");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		boardVO.setIp(ip);
		
		if ( boardBiz.writeArticle(boardVO) ){
			//list페이지로 이동한다.
			response.sendRedirect("/board/list");//브라우저의 url을 바꾸는 명령
		}
		else{
			response.sendRedirect("/board/write");
		}
	
	}

}
