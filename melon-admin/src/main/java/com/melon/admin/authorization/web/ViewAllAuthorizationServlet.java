package com.melon.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class ViewAllAuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthorizationService authorizationService;

	public ViewAllAuthorizationServlet() {
		authorizationService = new AuthorizationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 권한을 돌려주기위해 하는 작업임.

		String pageNo = request.getParameter("pageNo");

		if (pageNo == null || pageNo.length() == 0) {
			pageNo = "0";
		}

		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(pageNo);

		List<AuthorizationVO> authorizationList = authorizationService.getAllAuthorization(authorizationSearchVO);

		StringBuffer json = new StringBuffer();
		json.append(" { ");
		json.append(" \"status\": \"success\", ");
		json.append(" \"size\":" + authorizationList.size() + ", ");
		json.append(" \"pageNo\":" + pageNo + ", ");
		json.append(" \"authorizations\": [ ");

		String authorizationData = "";
		for (AuthorizationVO authorizationVO : authorizationList) {
			authorizationData += String.format(
					"{ \"authorizationId\": \"%s\", \"authorizationName\": \"%s\", \"parentAuthorizationId\": \"%s\" },",
					authorizationVO.getAuthorizationId(), authorizationVO.getAuthorizationName(),authorizationVO.getParentAuthorizationId());
		}

		if (authorizationData.length() > 0) {
			authorizationData = authorizationData.substring(0, authorizationData.length() - 1); // authorizationData의 마지막에 , 을 잘라라.
		}

		json.append(authorizationData);

		json.append(" ] ");
		json.append(" } ");

		PrintWriter writer = response.getWriter();
		writer.write(json.toString()); // 창 닫는 소스
		writer.flush();
		writer.close();

	}

}
