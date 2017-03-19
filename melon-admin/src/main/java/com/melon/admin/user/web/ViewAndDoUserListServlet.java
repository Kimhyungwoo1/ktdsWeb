package com.melon.admin.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.web.pager.ClassicPageExplorer;
import com.melon.admin.common.web.pager.PageExplorer;
import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class ViewAndDoUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private AuthorizationService authorizationService;

	public ViewAndDoUserListServlet() {
		userService = new UserServiceImpl();
		authorizationService = new AuthorizationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNo = request.getParameter("pageNo");

		UserSearchVO userSearchVO = new UserSearchVO();
		userSearchVO.getPager().setPageNumber(pageNo);
		
		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(pageNo);

		List<UserVO> userList = userService.getAllUser(userSearchVO);
		List<AuthorizationVO> authList = authorizationService.getAllAuthorization(authorizationSearchVO);

		PageExplorer pager = new ClassicPageExplorer(userSearchVO.getPager());

		request.setAttribute("userList", userList);
		request.setAttribute("authList", authList);
		request.setAttribute("userCount", userSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");
		dispatcher.forward(request, response);

	}

}
