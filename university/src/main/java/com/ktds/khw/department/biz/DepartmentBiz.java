package com.ktds.khw.department.biz;

import java.util.List;

import com.ktds.khw.department.vo.DepartmentVO;

public interface DepartmentBiz {
	
	public List<DepartmentVO> allDepartmentList();
	
	public Boolean addDepartment(DepartmentVO departmentVO);
	
	public Boolean deleteDepartment(int departmentId);

}
