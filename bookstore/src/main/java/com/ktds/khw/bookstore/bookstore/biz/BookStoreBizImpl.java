package com.ktds.khw.bookstore.bookstore.biz;

import java.util.List;

import com.ktds.khw.bookstore.bookstore.dao.BookStoreDao;
import com.ktds.khw.bookstore.bookstore.dao.BookStoreDaoImpl;
import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public class BookStoreBizImpl implements BookStoreBiz{

	private BookStoreDao bookStoreDao;
	
	public BookStoreBizImpl () {
		bookStoreDao = new BookStoreDaoImpl();
	}
	
	@Override
	public List<BookStoreVO> printAllList() {
		return bookStoreDao.bookList();
	}

	@Override
	public boolean insertBook(BookStoreVO bookStoreVO) {
		return bookStoreDao.addBook(bookStoreVO) > 0;
	}

	@Override
	public boolean deleteBook(String bookName) {
		return bookStoreDao.deleteBook(bookName) > 0;
	}

	@Override
	public boolean updateBook(BookStoreVO bookStoreVO) {
		return bookStoreDao.updateBook(bookStoreVO) > 0;
	}

	@Override
	public BookStoreVO printOneBook(int bookId) {
		return bookStoreDao.oneBook(bookId);
	}
	
	

}