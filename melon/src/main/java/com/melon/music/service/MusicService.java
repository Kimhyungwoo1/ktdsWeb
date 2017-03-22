package com.melon.music.service;

import java.util.List;

import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;
import com.melon.user.vo.UserVO;

public interface MusicService {

	public boolean addNewMusic(MusicVO musicVO);
	
	public List<MusicVO> getAllMusic(MusicSearchVO musicSearchVO);
	
	public MusicVO getOneMusic(String musicId, UserVO userVO);
	
	public boolean deleteOneMusic(String musicId);
	
	public boolean updateLikeCount(String musicId);
	
	public boolean updateOneMusic(MusicVO musicVO);
}
