package com.ktds.khw.department.vo;

import com.ktds.khw.student.vo.StudentVO;

public class DepartmentVO {

	private int departmentId;
	private String departmentName;
	private String departmentKind;
	
	private StudentVO studentVO;

	public StudentVO getStudentVO() {
		if ( studentVO == null ){
			studentVO = new StudentVO();
		}
		return studentVO;
	}

	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentKind() {
		return departmentKind;
	}

	public void setDepartmentKind(String departmentKind) {
		this.departmentKind = departmentKind;
	}
	
}
