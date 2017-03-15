package com.ktds.khw.book.users.dao;

import com.ktds.khw.book.users.vo.UsersVO;

public interface UsersDao {

	public int addUser(UsersVO usersVO);
	
	public UsersVO oneUser(UsersVO usersVO);
	
	
}
