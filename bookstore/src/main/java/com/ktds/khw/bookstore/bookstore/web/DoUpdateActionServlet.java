package com.ktds.khw.bookstore.bookstore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.bookstore.bookstore.biz.BookStoreBiz;
import com.ktds.khw.bookstore.bookstore.biz.BookStoreBizImpl;
import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public class DoUpdateActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookStoreBiz bookStoreBiz;

	public DoUpdateActionServlet() {
		bookStoreBiz = new BookStoreBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookIdString = request.getParameter("bookId");
		String bookPriceString = request.getParameter("bookPrice");
		int bookId = 0;
		int bookPrice = 0;
		
		try {
			bookId = Integer.parseInt(bookIdString);
			bookPrice = Integer.parseInt(bookPriceString);
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 접근경로 입니다.");
		}
		
		BookStoreVO bookStoreVO = new BookStoreVO();
		bookStoreVO.setBookId(bookId);
		bookStoreVO.setBookName(request.getParameter("bookName"));
		bookStoreVO.setBookPrice(bookPrice);
		bookStoreVO.setBookAuthor(request.getParameter("bookAuthor"));
		bookStoreVO.setPublisher(request.getParameter("publisher"));
		
		if ( bookStoreBiz.updateBook(bookStoreVO)) {
			response.sendRedirect("/bookstore/list");
		}
		else {
			response.sendRedirect("/bookstore/update");
		}
		
		
	}

}
