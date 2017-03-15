package com.ktds.khw.student.vo;

public class StudentVO {
	
	private int studentId;
	private String studentName;
	private String studentClass;
	private String studentSx;
	private int departmentId;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentSx() {
		return studentSx;
	}

	public void setStudentSx(String studentSx) {
		this.studentSx = studentSx;
	}

}
