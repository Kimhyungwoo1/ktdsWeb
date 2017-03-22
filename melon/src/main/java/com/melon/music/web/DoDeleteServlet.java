package com.melon.music.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.common.constants.AuthConst;
import com.melon.music.service.MusicService;
import com.melon.music.service.MusicServiceImpl;
import com.melon.user.vo.UserVO;

public class DoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicService musicService;

	public DoDeleteServlet() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER)) {
			response.sendError(404);
		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {

			String musicId = request.getParameter("musicId");

			boolean musicDelete = musicService.deleteOneMusic(musicId);

			if (musicDelete) {
				PrintWriter writer = response.getWriter();
				writer.write("ok");
				writer.flush();
				writer.close();
			} else {
				PrintWriter writer = response.getWriter();
				writer.write("fail");
				writer.flush();
				writer.close();
			}

		}
	}

}
