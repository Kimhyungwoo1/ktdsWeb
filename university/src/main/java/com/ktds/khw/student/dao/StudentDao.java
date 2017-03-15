package com.ktds.khw.student.dao;

import java.util.List;

import com.ktds.khw.student.vo.StudentVO;

public interface StudentDao {
	
	public List<StudentVO> studentList();
	
	public int insertStudentVO(StudentVO studentVO);
	
	public int deleteStudent(int studentId);
	
}
