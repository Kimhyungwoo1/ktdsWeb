package com.ktds.khw.student.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.student.biz.StudentBiz;
import com.ktds.khw.student.biz.StudentBizImpl;
import com.ktds.khw.student.vo.StudentVO;

public class ViewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentBiz studentBiz;
    
	public ViewStudentServlet() {
    	studentBiz = new StudentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<StudentVO> studentList = studentBiz.allStudentList();
		request.setAttribute("studentList", studentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/university/studentList.jsp");
		dispatcher.forward(request, response);
	}

}
