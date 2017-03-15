package com.ktds.khw.bookstore.bookstore.dao;

import java.util.List;

import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public interface BookStoreDao {

	public List<BookStoreVO> bookList();
	
	public int addBook(BookStoreVO bookStoreVO);
	
	public int deleteBook(String bookName);
	
	public int updateBook(BookStoreVO bookStoreVO);
	
	public BookStoreVO oneBook(int bookId);
	
}
