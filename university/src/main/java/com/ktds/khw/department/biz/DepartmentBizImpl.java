package com.ktds.khw.department.biz;

import java.util.List;

import com.ktds.khw.department.dao.DepartmentDao;
import com.ktds.khw.department.dao.DepartmentDaoImpl;
import com.ktds.khw.department.vo.DepartmentVO;

public class DepartmentBizImpl implements DepartmentBiz{
	
	private DepartmentDao departmentDao;
	
	public DepartmentBizImpl () {
		departmentDao = new DepartmentDaoImpl();
	}

	@Override
	public List<DepartmentVO> allDepartmentList() {
		return departmentDao.departmentList();
	}

	@Override
	public Boolean addDepartment(DepartmentVO departmentVO) {
		return departmentDao.insertDepartment(departmentVO) > 0;
	}
	
	@Override
	public Boolean deleteDepartment(int departmentId) {
		return departmentDao.deleteDepartment(departmentId) > 0;
	}
	

}
