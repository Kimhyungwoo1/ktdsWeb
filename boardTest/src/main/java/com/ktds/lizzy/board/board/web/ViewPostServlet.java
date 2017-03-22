package com.ktds.lizzy.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.common.web.DownloadUtil;

public class ViewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;
	
	public ViewPostServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String boardIdStr = request.getParameter("boardId");
		int boardId = 0;
		try {
		boardId = Integer.parseInt(boardIdStr);
		}
		catch(NumberFormatException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		BoardVO boardVO = boardBiz.getOneArticle(boardId);
		
		String postPath = "D:\\boardUploadFiles\\";
		
		DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
		downloadUtil.download(request, response, boardVO.getPoster(), boardVO.getPoster());
	}

}
