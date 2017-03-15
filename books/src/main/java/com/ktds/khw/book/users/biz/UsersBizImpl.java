package com.ktds.khw.book.users.biz;

import com.ktds.khw.book.users.dao.UsersDao;
import com.ktds.khw.book.users.dao.UsersDaoImpl;
import com.ktds.khw.book.users.vo.UsersVO;

public class UsersBizImpl implements UsersBiz{
	
	private UsersDao usersDao;
	
	public UsersBizImpl() {
		usersDao = new UsersDaoImpl();
	}

	@Override
	public Boolean insertUser(UsersVO usersVO) {
		return usersDao.addUser(usersVO) > 0;
	}

	@Override
	public UsersVO selectUser(UsersVO usersVO) {
		return usersDao.oneUser(usersVO);
	}

}
