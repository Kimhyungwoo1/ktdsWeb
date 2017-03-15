package com.melon.admin.authorization.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.dao.AuthorizationDao;
import com.melon.admin.authorization.dao.AuthorizationDaoImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.web.pager.Pager;

public class AuthorizationBizImpl implements AuthorizationBiz{
	
	private AuthorizationDao authorizationDao;
	
	public AuthorizationBizImpl () {
		authorizationDao = new AuthorizationDaoImpl();
	}

	@Override
	public List<AuthorizationVO> getAllAuthorization(AuthorizationSearchVO authorizationSearchVO) {
		
		int menuCount = authorizationDao.selectAllAuthorizationCount(authorizationSearchVO);
		
		Pager pager = authorizationSearchVO.getPager();
		pager.setTotalArticleCount(menuCount);
		
		if (menuCount == 0){
			new ArrayList<AuthorizationVO>();
		}
		
		return authorizationDao.selectAllAuthorization(authorizationSearchVO);
	}

	@Override
	public boolean registNewAuthorization(AuthorizationVO authorizationVO) {
		
		String id = authorizationDao.generateNewAuthorizationId();
		authorizationVO.setAuthorizationId(id);
		
		return authorizationDao.insertNewAuthorization(authorizationVO) > 0;
	}

	@Override
	public boolean updateAuthorizationInfo(AuthorizationVO authorizationVO) {
		return authorizationDao.updateAuthorizationInfo(authorizationVO) > 0;
	}

	@Override
	public boolean deleteAuthorization(String authorizationId) {
		return authorizationDao.deleteOneAuthorization(authorizationId) > 0;
	}

}
