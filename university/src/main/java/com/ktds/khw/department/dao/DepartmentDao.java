package com.ktds.khw.department.dao;

import java.util.List;

import com.ktds.khw.department.vo.DepartmentVO;

public interface DepartmentDao {

	public List<DepartmentVO> departmentList();
	
	public int insertDepartment(DepartmentVO departmentVO);
	
	public int deleteDepartment(int departmentId);
	
}
