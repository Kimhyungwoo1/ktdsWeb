package com.melon.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.melon.admin.authorization.biz.AuthorizationBiz;
import com.melon.admin.authorization.biz.AuthorizationBizImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.user.biz.UserBiz;
import com.melon.admin.user.biz.UserBizImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	private AuthorizationBiz authorizationBiz;

	public UserServiceImpl() {
		userBiz = new UserBizImpl();
		authorizationBiz = new AuthorizationBizImpl();
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
	public Map<String, Object> getOneUserWithAuthorization(String userId) {

		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(0);

		Map<String, Object> user = new HashMap<String, Object>();
		user.put("user", userBiz.getOneUser(userId));
		user.put("authorizations", authorizationBiz.getAllAuthorization(authorizationSearchVO));

		return user;
	}

	@Override
	public boolean registNewUser(UserVO userVO) {
		return userBiz.registNewUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO userVO) {
		UserVO tempUserVO = getOneUser(userVO.getUserId());

		if (userVO.getAuthorizationId() != null && userVO.getAuthorizationId().length() > 0) { // 권한정보가
																								// 있거나
																								// 권한정보를
																								// 수정했다면
			tempUserVO.setAuthorizationId(userVO.getAuthorizationId());
		}
		if (userVO.getUserPoint() > 0) { // 포인트의 데이터가 있다면
			tempUserVO.setUserPoint(userVO.getUserPoint());
		}
		if (userVO.getUserPassword() != null && userVO.getUserPassword().length() != 0) { // 페스워드
																							// 정보가
																							// 있거나
																							// 페스워드를
																							// 수정하지
																							// 했다면
			tempUserVO.setUserPassword(userVO.getUserPassword());
		}
		return userBiz.updateUser(tempUserVO);
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userBiz.deleteOneUser(userId);
	}

	@Override
	public boolean updateAllUser(String authAfter, String authBefore) {
		return userBiz.updateAllAuthorization(authAfter, authBefore);
	}

	@Override
	public boolean updateUserAuthorization(String[] userArray, String authAfter, String authBefore) {

		UserVO userVO = null;
		boolean isSuccess = false;

		for (String userId : userArray) {
			userVO = new UserVO();
			userVO.setUserId(userId);
			userVO.setAuthorizationId(authAfter);
			isSuccess = updateUser(userVO);

			if (!isSuccess) {
				return false;
			}
		}
		return true;
	}

}
