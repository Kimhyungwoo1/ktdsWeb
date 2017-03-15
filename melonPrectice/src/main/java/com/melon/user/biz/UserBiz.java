package com.melon.user.biz;

import com.melon.user.vo.UserVO;

public interface UserBiz {

	public boolean registNewUser(UserVO userVO);

	public UserVO loginUser(UserVO userVO);

	public boolean managePoint(String userId, int point);

	public boolean isDuplicateUserId(String userId);

}
