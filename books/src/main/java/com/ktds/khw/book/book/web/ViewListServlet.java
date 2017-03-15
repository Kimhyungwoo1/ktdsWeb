package com.ktds.khw.book.book.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.book.biz.BookBiz;
import com.ktds.khw.book.book.biz.BookBizImpl;
import com.ktds.khw.book.book.vo.BookSearchVO;
import com.ktds.khw.book.book.vo.BookVO;
import com.ktds.khw.book.common.web.pager.Pager;
import com.ktds.khw.book.common.web.pager.PagerFactory;

public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookBiz bookBiz;
	
    public ViewListServlet() {
    	bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		BookSearchVO serchVO = new BookSearchVO();
		serchVO.setPager(pager);
		
		List<BookVO> bookList = bookBiz.bookList(serchVO);
		request.setAttribute("bookList", bookList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/books/list.jsp");
		dispatcher.forward(request, response);
		
	}

}
