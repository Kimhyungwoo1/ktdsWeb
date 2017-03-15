package com.ktds.khw.book.book.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.book.book.biz.BookBiz;
import com.ktds.khw.book.book.biz.BookBizImpl;
import com.ktds.khw.book.book.vo.BookVO;

public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BookBiz bookBiz;
	
    public ViewDetailServlet() {
    	bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookIdString = request.getParameter("bookId");
		int bookId = 0;
		try {
			bookId = Integer.parseInt(bookIdString);
		}
		catch (NumberFormatException e){
			throw new RuntimeException("잘못된 접근 방법입니다.");
		}
		BookVO bookVO = bookBiz.oneBook(bookId);
		
		String index = bookVO.getIndex();
		index = index.replaceAll("\n", "<br/>");
		bookVO.setIndex(index);
		
		request.setAttribute("book", bookVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/books/detail.jsp");
		dispatcher.forward(request, response);
 		
	}

}
