package com.melon.admin.user.service;

import java.util.List;

import com.melon.admin.user.biz.UserBiz;
import com.melon.admin.user.biz.UserBizImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserServiceImpl implements UserService{
	
	private UserBiz userBiz;
	
	public UserServiceImpl () {
		userBiz = new UserBizImpl();
	}

	@Override
	public List<UserVO> getAllUser(UserSearchVO userSearchVO) {
		return userBiz.getAllUser(userSearchVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userBiz.getOneUser(userId);
	}

	@Override
	public boolean registNewUser(UserVO userVO) {
		return userBiz.registNewUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO userVO) {
		return userBiz.updateUser(userVO);
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userBiz.deleteOneUser(userId);
	}

}
