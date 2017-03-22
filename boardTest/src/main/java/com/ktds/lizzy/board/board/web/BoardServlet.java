package com.ktds.lizzy.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.lizzy.board.board.biz.BoardBiz;
import com.ktds.lizzy.board.board.biz.BoardBizImpl;
import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.board.user.vo.UserVO;
import com.ktds.lizzy.common.web.pager.ClassicPageExplorer;
import com.ktds.lizzy.common.web.pager.PageExplorer;
import com.ktds.lizzy.common.web.pager.Pager;
import com.ktds.lizzy.common.web.pager.PagerFactory;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public BoardServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		BoardSearchVO searchVO = new BoardSearchVO();
		searchVO.setPager(pager);
		List<BoardVO> articleList = boardBiz.getAllArticles(searchVO);
		
		
		PageExplorer pageExplorer = new ClassicPageExplorer(pager); // paging list�� ������ִ� ��
		String pages = pageExplorer.getPagingList("pageNo", "[@]", "PREV", "NEXT", "searchForm");
		
		request.setAttribute("articles", articleList);
		request.setAttribute("count", pager.getTotalArticleCount());
		request.setAttribute("pager", pages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/board.jsp");
		dispatcher.forward(request, response);
	}

}
