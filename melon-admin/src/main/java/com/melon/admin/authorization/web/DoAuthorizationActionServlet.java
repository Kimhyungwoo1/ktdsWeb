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

public class DoAuthorizationActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthorizationService authorizationService;
	
	public DoAuthorizationActionServlet() {
		authorizationService = new AuthorizationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendError(404);	//그런 페이지 없다 라는뜻, 반듯이 post로만 들어오게 하기위해 사용.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authorizationName = request.getParameter("authorizationName");
		String parentAuthorizationId = request.getParameter("parentAuthorizationId");
		
		AuthorizationVO authorizationVO = new AuthorizationVO();
		authorizationVO.setAuthorizationName(authorizationName);
		authorizationVO.setParentAuthorizationId(parentAuthorizationId);
		
		boolean isSuccess = authorizationService.registNewAuthorization(authorizationVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("authorization", authorizationVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}

}
