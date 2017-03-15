package com.ktds.khw.board.user.biz;

import com.ktds.khw.board.user.dao.UserDao;
import com.ktds.khw.board.user.dao.UserDaoImpl;
import com.ktds.khw.board.user.vo.UserVO;

public class UserBizImpl implements UserBiz{
	
	private UserDao userDao;
	
	public UserBizImpl () {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean insertUser(UserVO userVO) {
		return userDao.addUser(userVO) > 0;
	}

	@Override
	public UserVO printSignIn(UserVO userVO) {
		return userDao.signIn(userVO);
	}

}
