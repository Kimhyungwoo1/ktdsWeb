package com.melon.music.vo;

import com.melon.common.web.pager.Pager;
import com.melon.common.web.pager.PagerFactory;

public class MusicSearchVO {
	
	private Pager pager;
	
	private String albumId;
	private String artistId;

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE, 20, 10); // 앞에 20 은 한페이지의 20개씩 보여주고 뒤에 10은 10개의 페이지를 보여주겠다.
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

}
