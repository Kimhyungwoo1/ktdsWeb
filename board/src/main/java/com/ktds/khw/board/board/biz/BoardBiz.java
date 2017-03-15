package com.ktds.khw.board.board.biz;

import java.util.List;

import com.ktds.khw.board.board.vo.BoardVO;

/**
 * Created by kimhyungwoo on 2017. 2. 17..
 */
public interface BoardBiz {

    public Boolean writeArticle(BoardVO boardVO);

    public List<BoardVO> getAllArticles();

    public BoardVO getArticles(int boardVO);

    public boolean deleteArticles(int number);
    
    public boolean updateArticles(BoardVO boardVO);

}
