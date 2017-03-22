package com.ktds.lizzy.board.board.dao;

import java.util.List;

import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardDao {
	
	public int selectAllArticlesCount(BoardSearchVO searchVO);
	
    public List<BoardVO> selectAllArticles(BoardSearchVO searchVO);

    public BoardVO selectOneArticle(int boardId);
    
    public int insertArticle(BoardVO boardVO);

    public int removeArticle(int boardId);
    
    public int updateArticle(BoardVO boardVO);

}
