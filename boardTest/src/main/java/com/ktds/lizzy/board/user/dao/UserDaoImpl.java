package com.ktds.lizzy.board.user.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.ktds.lizzy.board.user.vo.UserVO;
import com.ktds.lizzy.dao.support.*;
import com.ktds.lizzy.dao.support.annotation.BindData;

public class UserDaoImpl extends JDBCDaoSupport implements UserDao {

	@Override
	public int insertUser(UserVO userVO) {
		return update(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" INSERT  INTO    USERS   ( "             );
				query.append("                             USR_ID, "  );
				query.append("                             USR_NM, "  );
				query.append("                             USR_PWD, " );
				query.append("                             JOIN_DT "   );
				query.append("                         ) "             );
				query.append("                 VALUES  ( "             );
				query.append("                             ?, "        );
				query.append("                             ?, "        );
				query.append("                             ?, "        );
				query.append("                             SYSDATE "         );
				query.append("                         ) "             );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, userVO.getUserId());
				stmt.setString(2, userVO.getUserName());
				stmt.setString(3, userVO.getUserPassword());
			}
			
			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public UserVO selectOneUser(String userId, String userPassword) {
		return (UserVO) selectOne(new QueryHandler() {
			
			@Override
			public String prepareQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT  USR_ID, "    );
				query.append("         USR_NM, "   );
				query.append("         USR_PWD, "    );
				query.append("         JOIN_DT "    );
				query.append(" FROM    USERS "      );
				query.append(" WHERE   USR_ID  = ? " );
				query.append(" AND     USR_PWD = ? " );

				return query.toString();
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, userId);
				stmt.setString(2, userPassword);
			}
			
			@Override
			public Object getData(ResultSet rs) {
				UserVO user = new UserVO();
				BindData.bindData(rs, user);
				return user;
			}
		});
	}
	
}
