package com.ktds.khw.book.book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.book.biz.BookBiz;
import com.ktds.khw.book.book.biz.BookBizImpl;
import com.ktds.khw.book.book.vo.BookVO;

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookBiz bookBiz;
	
    public DoModifyActionServlet() {
		bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookIdString = request.getParameter("bookId");
		int bookId;
		
		try {
			bookId = Integer.parseInt(bookIdString);
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		BookVO bookVO = new BookVO();
		bookVO.setBookId(bookId);
		bookVO.setBookName(request.getParameter("bookName"));
		bookVO.setBookSubName(request.getParameter("bookSubName"));
		bookVO.setIndex(request.getParameter("index"));
		
		if ( bookBiz.updateBook(bookVO) ) {
			response.sendRedirect("/books/list");
		}
		else {
			response.sendRedirect("/books/detail");
		}
		
	}

}
