package com.ktds.khw.book.book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.book.biz.BookBiz;
import com.ktds.khw.book.book.biz.BookBizImpl;
import com.ktds.khw.book.book.vo.BookVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookBiz bookBiz;
    public DoWriteActionServlet() {
    	bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookVO bookVO = new BookVO();
		bookVO.setBookName(request.getParameter("bookName"));
		bookVO.setBookSubName(request.getParameter("bookSubName"));
		bookVO.setIndex(request.getParameter("index"));
		
		if ( bookBiz.insertBook(bookVO) ) {
			response.sendRedirect("/books/list");
		}
		else {
			response.sendRedirect("/books/write");
		}
		
	}

}
