package com.ktds.lizzy.board.board.biz;

import java.util.List;

import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardBiz {

	public boolean writeArticle(BoardVO boardVO);

	public List<BoardVO> getAllArticles(BoardSearchVO searchVO);

	public BoardVO getOneArticle(int boardId);

	public boolean deleteArticle(int boardId);

	public boolean modifyArticle(BoardVO boardVO);

}
