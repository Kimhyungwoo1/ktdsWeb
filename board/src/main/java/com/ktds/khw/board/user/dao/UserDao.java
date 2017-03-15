package com.ktds.khw.board.user.dao;

import com.ktds.khw.board.user.vo.UserVO;

public interface UserDao {
	
	public int addUser(UserVO userVO);
	
	public UserVO signIn (UserVO userVO);

}
