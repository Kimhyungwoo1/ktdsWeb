package com.ktds.lizzy.board.user.dao;

import com.ktds.lizzy.board.user.vo.UserVO;

public interface UserDao {
	
	public int insertUser(UserVO userVO);
	public UserVO selectOneUser (String userId, String userPassword);

}
