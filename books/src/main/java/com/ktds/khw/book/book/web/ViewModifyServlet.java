package com.ktds.khw.book.book.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.book.biz.BookBiz;
import com.ktds.khw.book.book.biz.BookBizImpl;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookBiz bookBiz;
	
    public ViewModifyServlet() {
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
		
		//BookVO bookVO = bookBiz.oneBook(bookId);
		
		request.setAttribute("bookId", bookId);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/books/modify.jsp");
		dispatcher.forward(request, response);
		
	}

}
