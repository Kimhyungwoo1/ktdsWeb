package com.ktds.khw.bookstore.bookstore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.bookstore.bookstore.biz.BookStoreBiz;
import com.ktds.khw.bookstore.bookstore.biz.BookStoreBizImpl;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookStoreBiz bookStoreBiz;
	
    public DoDeleteActionServlet() {
    	bookStoreBiz = new BookStoreBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String bookName = request.getParameter("bookName");
		
		if ( bookStoreBiz.deleteBook(bookName) ) {
			response.sendRedirect("/bookstore/list");
		}
		else {
			response.sendRedirect("/bookstore/delete");
		}
		
	}

}
