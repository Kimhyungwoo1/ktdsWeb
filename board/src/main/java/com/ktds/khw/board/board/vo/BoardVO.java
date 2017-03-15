package com.ktds.khw.board.board.vo;

import com.ktds.khw.board.user.vo.UserVO;
import com.ktds.khw.dao.support.annotation.Types;

/**
 * Created by kimhyungwoo on 2017. 2. 17..
 */
public class BoardVO {

	@Types
	private int boardId;

	@Types
	private String subject;

	@Types
	private String content;

	@Types
	private String writer;

	@Types
	private int likeCount;

	@Types
	private String writeDate;

	@Types
	private String ip;

	private UserVO user;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UserVO getUser() {
		if ( user == null ) {
			UserVO user = new UserVO();
		}
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
