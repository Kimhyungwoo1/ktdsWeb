package com.melon.music.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.music.service.MusicService;
import com.melon.music.service.MusicServiceImpl;
import com.melon.music.vo.MusicVO;

public class DoLikeCoutnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicService musicService;
	
	public DoLikeCoutnServlet() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String musicId = request.getParameter("musicId");
		System.out.println(musicId);
		
		boolean musicChange = musicService.updateLikeCount(musicId);
		
		StringBuffer json = new StringBuffer();
		json.append(" { ");
		json.append(" \"status\": \"success\", ");
		json.append(" \"success\":" + musicChange);
		json.append(" } ");
		
		PrintWriter writer = response.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();

		
	}

}
