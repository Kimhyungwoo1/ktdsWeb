package com.ktds.lizzy.board.user.biz;

import com.ktds.lizzy.board.user.dao.UserDao;
import com.ktds.lizzy.board.user.dao.UserDaoImpl;
import com.ktds.lizzy.board.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	
	private UserDao userDao;
	
	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean signUpUser(UserVO userVO) {
		return userDao.insertUser(userVO) > 0;
	}

	@Override
	public UserVO checkUser(String userId, String userPassword) {
		return userDao.selectOneUser(userId, userPassword);
	}

}
