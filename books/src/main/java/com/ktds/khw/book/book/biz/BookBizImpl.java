package com.ktds.khw.book.book.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.khw.book.book.dao.BookDao;
import com.ktds.khw.book.book.dao.BookDaoImpl;
import com.ktds.khw.book.book.vo.BookSearchVO;
import com.ktds.khw.book.book.vo.BookVO;

public class BookBizImpl implements BookBiz {

	private BookDao bookDao;

	public BookBizImpl() {
		bookDao = new BookDaoImpl();
	}

	@Override
	public List<BookVO> bookList(BookSearchVO searchVO) {

		// TODO 게시글의 개수를 조회해 Pager객체에 할당한다.
		// 게시글의 개수가 0이라면, 비어있는 리스트를 리턴하고
		// 그렇지 않다면, allBookList(); 를 한다.

		int bookCount = bookDao.getBookListCount(searchVO);
		searchVO.getPager().setTotalArticleCount(bookCount);

		if (bookCount == 0) {
			return new ArrayList<BookVO>();
		} else {
			return bookDao.allBookList(searchVO);
		}
	}

	@Override
	public BookVO oneBook(int bookId) {
		return bookDao.selectBook(bookId);
	}

	@Override
	public boolean insertBook(BookVO bookVO) {
		return bookDao.insertBook(bookVO) > 0;
	}

	@Override
	public boolean deleteBook(int bookId) {
		return bookDao.deleteBook(bookId) > 0;
	}

	@Override
	public boolean updateBook(BookVO bookVO) {
		return bookDao.update(bookVO) > 0;
	}

}
