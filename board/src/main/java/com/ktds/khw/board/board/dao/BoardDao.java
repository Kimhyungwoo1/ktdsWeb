package com.ktds.khw.board.board.dao;

import java.util.List;

import com.ktds.khw.board.board.vo.BoardVO;

/**
 * Created by kimhyungwoo on 2017. 2. 17..
 */
public interface BoardDao {

    public int insertArticle(BoardVO boardVO);

    public List<BoardVO> selectAllArticles();

    public BoardVO selectArticles(int boardVO);

    public int deleteArticle(int number);

    public int updateArticle(BoardVO boardVO);


}
