package com.ktds.lizzy.board.user.biz;

import com.ktds.lizzy.board.user.vo.UserVO;

public interface UserBiz {
	
	public boolean signUpUser(UserVO userVO);
	public UserVO checkUser(String userId, String userPassword);

}
