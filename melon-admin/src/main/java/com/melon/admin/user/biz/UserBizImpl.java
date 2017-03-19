package com.melon.admin.user.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.admin.common.web.pager.Pager;
import com.melon.admin.user.dao.UserDao;
import com.melon.admin.user.dao.UserDaoImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserBizImpl implements UserBiz{
	
	private UserDao userDao;
	
	public UserBizImpl () {
		userDao = new UserDaoImpl();
	}

	@Override
	public List<UserVO> getAllUser(UserSearchVO userSearchVO) {
		
		int menuCount = userDao.selectCountUserId(userSearchVO);
		
		Pager pager = userSearchVO.getPager();
		pager.setTotalArticleCount(menuCount);
		
		if ( menuCount == 0 ) {
			return new ArrayList<UserVO>();
		}
		
		return userDao.selectAllUser(userSearchVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userDao.selectOneUser(userId);
	}

	@Override
	public boolean registNewUser(UserVO userVO) {
		return userDao.insertNewUser(userVO) > 0;
	}

	@Override
	public boolean updateUser(UserVO userVO) {
		return userDao.updateUserInfo(userVO) > 0;
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userDao.deleteOneUser(userId) > 0;
	}

	@Override
	public boolean updateAllAuthorization(String authAfter, String authBefore) {
		return userDao.updateAllAuthorization(authAfter, authBefore) > 0;
	}

}
