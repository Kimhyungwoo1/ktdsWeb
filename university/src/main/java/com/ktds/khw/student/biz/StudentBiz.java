package com.ktds.khw.student.biz;

import java.util.List;

import com.ktds.khw.student.vo.StudentVO;

public interface StudentBiz {

	public List<StudentVO> allStudentList();
	
	public Boolean addStudent(StudentVO studentVO);
	
	public Boolean deleteStudent(int studentId);
	
}
