package com.melon.music.service;

import java.util.List;

import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;
import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class MusicServiceImpl implements MusicService{
	
	private MusicBiz musicBiz;
	private UserBiz userBiz;
	
	public MusicServiceImpl() {
		musicBiz = new MusicBizImpl();
		userBiz = new UserBizImpl();
	}

	@Override
	public boolean updateLikeCount(String musicId) {
		return musicBiz.updateLikeCount(musicId);
	}
	
	@Override
	public boolean addNewMusic(MusicVO musicVO) {
		return musicBiz.addNewMusic(musicVO);
	}

	@Override
	public List<MusicVO> getAllMusic(MusicSearchVO musicSearchVO) {
		return musicBiz.getAllMusic(musicSearchVO);
	}

	@Override
	public MusicVO getOneMusic(String musicId, UserVO userVO) {
		
		
//		if ( music != null ) {
//			userBiz.managePoint(userVO.getUserId(), -5);
//			int userPoint = userVO.getUserPoint();
//			userVO.setUserPoint(userPoint - 5);
//		}
		
		return musicBiz.getOneMusic(musicId);
	}

	@Override
	public boolean deleteOneMusic(String musicId) {
		return musicBiz.deleteOneMusic(musicId);
	}

}
