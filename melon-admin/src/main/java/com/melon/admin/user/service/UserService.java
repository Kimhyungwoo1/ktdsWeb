package com.melon.admin.user.service;

import java.util.List;
import java.util.Map;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public interface UserService {

	public List<UserVO> getAllUser (UserSearchVO userSearchVO);
	
	public UserVO getOneUser (UserVO userVO);
	
	public UserVO getOneUser (String userId);
	public Map<String, Object> getOneUserWithAuthorization(String userId);
	
	public boolean registNewUser(UserVO userVO);
	
	public boolean updateUser(UserVO userVO);
	
	public boolean deleteOneUser(String userId);
	
}
