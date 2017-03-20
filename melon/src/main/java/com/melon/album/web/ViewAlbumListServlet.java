package com.melon.album.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.album.biz.AlbumBiz;
import com.melon.album.biz.AlbumBizImpl;
import com.melon.album.vo.AlbumSearchVO;
import com.melon.album.vo.AlbumVO;
import com.melon.common.constants.AuthConst;
import com.melon.common.web.pager.ClassicPageExplorer;
import com.melon.common.web.pager.PageExplorer;
import com.melon.user.vo.UserVO;

public class ViewAlbumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AlbumBiz albumBiz;
	
    public ViewAlbumListServlet() {
    	
    	albumBiz = new AlbumBizImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		String artistId = request.getParameter("artistId");
		
		AlbumSearchVO albumSearchVO = new AlbumSearchVO();
		albumSearchVO.setArtistId(artistId);
		albumSearchVO.getPager().setPageNumber(pageNo);
		
		List<AlbumVO> albumList = albumBiz.getAllAlbums(albumSearchVO);
		
		PageExplorer pager = new ClassicPageExplorer(albumSearchVO.getPager());
		
		request.setAttribute("albumList", albumList);
		request.setAttribute("albumCount", albumSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pageNo", pager.getPagingList("pageNo", "[@]", "이전", "다음", "SearchForm"));
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");
		
		request.setAttribute("isNormalUser", userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER));
		request.setAttribute("isOperatorUser", userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		request.setAttribute("isAdminUser", userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/album/list.jsp");
		dispatcher.forward(request, response);
		
		
	}

}