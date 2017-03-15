package com.ktds.khw.board.user.biz;

import com.ktds.khw.board.user.vo.UserVO;

public interface UserBiz {
	
	public boolean insertUser(UserVO userVO);
	
	public UserVO printSignIn(UserVO userVO);

}
