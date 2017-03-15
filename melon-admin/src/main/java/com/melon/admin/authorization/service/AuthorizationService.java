package com.melon.admin.authorization.service;

import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public interface AuthorizationService {

	public List<AuthorizationVO> getAllAuthorization (AuthorizationSearchVO authorizationSearchVO);
	
	public boolean registNewAuthorization (AuthorizationVO authorizationVO);
	
	public boolean updateAuthorizationInfo (AuthorizationVO authorizationVO);
	
	public boolean deleteAuthorization (String authorizationId);
	
}
