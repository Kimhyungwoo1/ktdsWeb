package com.ktds.khw.book.users.biz;

import com.ktds.khw.book.users.vo.UsersVO;

public interface UsersBiz {
	
	public Boolean insertUser(UsersVO usersVO);
	
	public UsersVO selectUser (UsersVO usersVO);

}
