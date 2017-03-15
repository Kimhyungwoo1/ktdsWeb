package com.ktds.khw.bookstore.bookstore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.bookstore.bookstore.biz.BookStoreBiz;
import com.ktds.khw.bookstore.bookstore.biz.BookStoreBizImpl;
import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookStoreBiz bookStoreBiz;
	
    public DoWriteActionServlet() {
    	bookStoreBiz = new BookStoreBizImpl();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String priceString = request.getParameter("bookPrice");
		int price = 0;
		
		try {
			price = Integer.parseInt(priceString);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("잘못된 접근방법 입니다.");
		}
		
		BookStoreVO bookStoreVO = new BookStoreVO();
		bookStoreVO.setBookName(request.getParameter("bookName"));
		bookStoreVO.setBookAuthor(request.getParameter("bookAuthor"));
		bookStoreVO.setBookPrice(price);
		bookStoreVO.setPublisher(request.getParameter("publisher"));
		
		if ( bookStoreBiz.insertBook(bookStoreVO) ){
			response.sendRedirect("/bookstore/list");
		}
		else {
			response.sendRedirect("/bookstore/write");
		}
	}

}
