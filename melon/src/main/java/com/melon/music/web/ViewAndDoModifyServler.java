package com.melon.music.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.common.constants.AuthConst;
import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;
import com.melon.music.service.MusicService;
import com.melon.music.service.MusicServiceImpl;
import com.melon.music.vo.MusicVO;
import com.melon.user.vo.UserVO;

public class ViewAndDoModifyServler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicService musicService;

	public ViewAndDoModifyServler() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String musicId = request.getParameter("musicId");

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		MusicVO musicVO = musicService.getOneMusic(musicId, userVO);

		request.setAttribute("music", musicVO);

		if (userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER)) {
			response.sendError(404);
		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/modify.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER)) {
			response.sendError(404);
		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {

			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String title = multipart.getParameter("title");
			String musician = multipart.getParameter("musician");
			String director = multipart.getParameter("director");
			String lyrics = multipart.getParameter("lyrics");
			String musicId = multipart.getParameter("musicId");
			MultipartFile mp3File = multipart.getFile("mp3File");

			if (mp3File != null && mp3File.getFileSize() > 0) {
				String path = "/Users/kimhyungwoo/melon/src/main/webapp/mp3/";
				path += musicId;

				File dir = new File(path);
				dir.mkdir();

				mp3File.write(path + File.separator + mp3File.getFileName());
			}

			MusicVO musicVO = new MusicVO();
			musicVO.setTitle(title);
			musicVO.setMusician(musician);
			musicVO.setDirector(director);
			musicVO.setLyrics(lyrics);
			musicVO.setMusicId(musicId);

			boolean musicOne = musicService.updateOneMusic(musicVO);

			if (musicOne) {
				PrintWriter writer = response.getWriter();
				StringBuffer sb = new StringBuffer();
				sb.append("<script type='text/javascript'>");
				sb.append("      opener.location.reload(); ");
				sb.append("      self.close(); ");
				sb.append("</script>");
				writer.write(sb.toString());
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
