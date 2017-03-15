package com.ktds.khw.student.biz;

import java.util.List;

import com.ktds.khw.student.dao.StudentDao;
import com.ktds.khw.student.dao.StudentDaoImpl;
import com.ktds.khw.student.vo.StudentVO;

public class StudentBizImpl implements StudentBiz{
	
	private StudentDao studentDao;
	
	public StudentBizImpl () {
		studentDao = new StudentDaoImpl();
	}

	@Override
	public List<StudentVO> allStudentList() {
		return studentDao.studentList();
	}

	@Override
	public Boolean addStudent(StudentVO studentVO) {
		return studentDao.insertStudentVO(studentVO) > 0;
	}

	@Override
	public Boolean deleteStudent(int studentId) {
		return studentDao.deleteStudent(studentId) > 0;
	}

}
