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

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookStoreBiz bookStoreBiz;
	
    public ListServlet() {
    	bookStoreBiz = new BookStoreBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		List<BookStoreVO> bookList = bookStoreBiz.printAllList();
		request.setAttribute("bookList", bookList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
		dispatcher.forward(request, response);
	}

}
