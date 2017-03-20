package com.melon.artist.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.artist.biz.ArtistBiz;
import com.melon.artist.biz.ArtistBizImpl;
import com.melon.artist.vo.ArtistSearchVO;
import com.melon.artist.vo.ArtistVO;
import com.melon.common.constants.AuthConst;
import com.melon.common.web.pager.ClassicPageExplorer;
import com.melon.common.web.pager.PageExplorer;
import com.melon.user.vo.UserVO;

public class ViewArtistListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArtistBiz artistBiz;
	
    public ViewArtistListServlet() {
    	artistBiz = new ArtistBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		
		ArtistSearchVO artistSearchVO = new ArtistSearchVO();
		artistSearchVO.getPager().setPageNumber(pageNo);
		
		List<ArtistVO> artistList = artistBiz.getAllArtist(artistSearchVO);
		
		PageExplorer pager = new ClassicPageExplorer(artistSearchVO.getPager());
		
		request.setAttribute("artistList", artistList);
		request.setAttribute("artistCount", artistSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "이전", "다음", "searchFrom"));
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");
		
		//forward 하는 page에선 권한 구분하는 것이 다 필요
		request.setAttribute("isNormalUser", userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER) );
		request.setAttribute("isOperatorUser", userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		request.setAttribute("isAdminUser", userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/artist/list.jsp");
		dispatcher.forward(request, response);
		
	}

}
