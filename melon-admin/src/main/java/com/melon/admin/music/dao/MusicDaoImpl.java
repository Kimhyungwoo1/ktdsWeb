package com.melon.admin.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.music.vo.MusicVO;

public class MusicDaoImpl implements MusicDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";
	
	@Override
	public List<MusicVO> allRankList() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");

			StringBuffer query = new StringBuffer();
			query.append("	SELECT ");
			query.append("			 TTL ");
			query.append("			, LK_CNT ");
			query.append("			, MSCN ");
			query.append("	FROM		MSC ");
			query.append("	ORDER	BY LK_CNT DESC ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			List<MusicVO> musicList = new ArrayList<MusicVO>();
			MusicVO musicVO = null;
			
			while( rs.next() ){
				musicVO = new MusicVO();
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMusician(rs.getString("MSCN"));
				
				musicList.add(musicVO);
			}
			return musicList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}
}
