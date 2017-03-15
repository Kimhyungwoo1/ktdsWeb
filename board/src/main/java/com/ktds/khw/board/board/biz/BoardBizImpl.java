package com.ktds.khw.board.board.biz;

import java.util.List;

import com.ktds.khw.board.board.dao.BoardDao;
import com.ktds.khw.board.board.dao.BoardDaoImpl;
import com.ktds.khw.board.board.vo.BoardVO;

/**
 * Created by kimhyungwoo on 2017. 2. 17..
 */
public class BoardBizImpl implements BoardBiz {

	private BoardDao boardDao;

	public BoardBizImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public Boolean writeArticle(BoardVO boardVO) {

		return boardDao.insertArticle(boardVO) > 0;
	}

	@Override
	public List<BoardVO> getAllArticles() {

		return boardDao.selectAllArticles();
	}

	@Override
	public BoardVO getArticles(int boardVO) {
		return boardDao.selectArticles(boardVO);
	}

	@Override
	public boolean deleteArticles(int number) {
		return boardDao.deleteArticle(number) > 0;
	}

	@Override
	public boolean updateArticles(BoardVO boardVO) {
		return boardDao.updateArticle(boardVO) > 0;
	}
}
