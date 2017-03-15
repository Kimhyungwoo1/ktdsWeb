package com.ktds.khw.bookstore.bookstore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.bookstore.bookstore.biz.BookStoreBiz;
import com.ktds.khw.bookstore.bookstore.biz.BookStoreBizImpl;
import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public class ViewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookStoreBiz bookStoreBiz;
	
	public ViewUpdateServlet() {
		bookStoreBiz = new BookStoreBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BookStoreVO> bookList = bookStoreBiz.printAllList();
		request.setAttribute("bookList", bookList);
		
		String bookIdString = request.getParameter("bookId");
		int bookId = 0;
		try {
			bookId = Integer.parseInt(bookIdString);
		}
		catch (NumberFormatException e){
			throw new RuntimeException("잘못된 접근 방법입니다.");
		}
		
		BookStoreVO bookStoreVO = bookStoreBiz.printOneBook(bookId);
		request.setAttribute("bookstore", bookStoreVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
		dispatcher.forward(request, response);
	}

}
