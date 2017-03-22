package com.ktds.lizzy.board.board.web;

import java.io.File;
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
import com.ktds.lizzy.common.web.MultipartHttpServletRequest;
import com.ktds.lizzy.common.web.MultipartHttpServletRequest.MultipartFile;

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
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
		String subject = multipart.getParameter("subject");
		
		String contents = multipart.getParameter("contents");
		contents = contents.replaceAll("\n", "<br/>");
		contents = contents.replaceAll("\r", "");
		
		String posterFileName = "";
				
		MultipartFile post= multipart.getFile("poster");
		
		if (post != null && post.getFileSize() > 0) {
			posterFileName = post.getFileName();
			
			post.write( "D:\\boardUploadFiles\\" + File.separator + post.getFileName());
		}
				
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("_USER_"); // Session Container에서 내 정보를 가져온다.
		
		BoardVO boardVO = new BoardVO();
		
		boardVO.setWriter(user.getUserId());
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setSubject(subject);
		boardVO.setContents(contents);
		boardVO.setPoster(posterFileName);
		
		if (boardBiz.writeArticle(boardVO)) {
			//board 페이지로 이동
			// Redirect 방식
			response.sendRedirect("/boardTest/board");
		}
		else {
			//write 페이지로 이동
			// Redirect 방식
			response.sendRedirect("/boardTest/write");
		}

	}

}
