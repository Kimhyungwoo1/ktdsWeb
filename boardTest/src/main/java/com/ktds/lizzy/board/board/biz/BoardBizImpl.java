package com.ktds.lizzy.board.board.biz;

import com.ktds.lizzy.board.board.dao.BoardDao;
import com.ktds.lizzy.board.board.dao.BoardDaoImpl;
import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardBizImpl implements BoardBiz {

	private BoardDao boardDao;

	public BoardBizImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public boolean writeArticle(BoardVO boardVO) {
		return boardDao.insertArticle(boardVO) > 0;
	}

	@Override
	public List<BoardVO> getAllArticles(BoardSearchVO searchVO) {
		// TODO: 게시글의 개수를 조회해 Pager객체에 할당한다.
		// 게시글의 개수가 0이라면, 비어있는 리스트를 리턴하고
		// 그렇지 않다면, selectAllArticles()를 리턴한다.
		int articleCount = boardDao.selectAllArticlesCount(searchVO);
		searchVO.getPager().setTotalArticleCount(articleCount);
		
		if (articleCount == 0) {
			return new ArrayList<BoardVO>();
		}
		else {
			return boardDao.selectAllArticles(searchVO);
		}
	}

	@Override
	public BoardVO getOneArticle(int boardId) {
		return boardDao.selectOneArticle(boardId);
	}

	@Override
	public boolean deleteArticle(int boardId) {
		return boardDao.removeArticle(boardId) > 0;
	}

	@Override
	public boolean modifyArticle(BoardVO boardVO) {
		return boardDao.updateArticle(boardVO) > 0;
	}

}
