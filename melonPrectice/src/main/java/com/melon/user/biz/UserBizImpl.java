package com.melon.user.biz;

import com.melon.user.dao.UserDao;
import com.melon.user.dao.UserDaoImpl;
import com.melon.user.vo.UserVO;

public class UserBizImpl implements UserBiz{

	private UserDao userDao;
	
	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean registNewUser(UserVO userVO) {
		boolean isSuccess = userDao.insertNewUser(userVO) > 0;
		
		if ( managePoint ) {
			
		}
	}

	@Override
	public UserVO loginUser(UserVO userVO) {
		return null;
	}

	@Override
	public boolean managePoint(String userId, int point) {
		return userDao.updatePoint(userId, point) > 0;
	}

	@Override
	public boolean isDuplicateUserId(String userId) {
		return userDao.selectCountByUserId(userId) > 0;
	}

}
