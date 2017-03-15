package com.melon.user.dao;

import com.melon.user.vo.UserVO;

public interface UserDao {

	public int insertNewUser(UserVO userVO);
	
	public UserVO selectOneUser(UserVO userVO);
	
	public int updatePoint(String userId, int point);
	
	public int selectCountByUserId(String userId);
	
}
