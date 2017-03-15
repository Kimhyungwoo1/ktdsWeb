package com.ktds.khw.department.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.department.biz.DepartmentBiz;
import com.ktds.khw.department.biz.DepartmentBizImpl;
import com.ktds.khw.department.vo.DepartmentVO;

public class ViewDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentBiz departmentBiz;
	
    public ViewDepartmentServlet() {
    	departmentBiz = new DepartmentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<DepartmentVO> departmentList = departmentBiz.allDepartmentList();
		request.setAttribute("departmentList", departmentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/university/departmentList.jsp");
		dispatcher.forward(request, response);
		
	}

}
