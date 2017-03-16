package com.melon.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthorizationService authorizationService;
	
	public ViewModifyServlet() {
		authorizationService = new AuthorizationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authorizationId = request.getParameter("authorizationId");
		String authorizationName = request.getParameter("authorizationName");
		String parentAuthorizationId = request.getParameter("parentAuthorizationId");
		
		AuthorizationVO authorizationVO = new AuthorizationVO();
		authorizationVO.setAuthorizationId(authorizationId);
		authorizationVO.setAuthorizationName(authorizationName);
		authorizationVO.setParentAuthorizationId(parentAuthorizationId);
		
		boolean isSuccess = authorizationService.updateAuthorizationInfo(authorizationVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", isSuccess ? "success" : "fail");
		map.put("authorization", authorizationVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		/*
		 * {
		 * 		status : 'success',
		 * 		authorizationId = 'at-2013311232-123124123
		 * }
		 */
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}

}
