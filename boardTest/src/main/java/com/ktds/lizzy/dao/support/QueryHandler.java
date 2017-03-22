package com.ktds.lizzy.dao.support;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryHandler {
	
	public String prepareQuery();
	public void mappingParameters(PreparedStatement stmt) throws SQLException;
	public Object getData(ResultSet rs);
	
}
