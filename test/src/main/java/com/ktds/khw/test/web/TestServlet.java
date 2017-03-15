package com.ktds.khw.test.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.khw.test.vo.IntroduceVO;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test.jsp");
		
		IntroduceVO introduceVO = new IntroduceVO();
		introduceVO.setName("김형우");
		introduceVO.setAge("26세");
		introduceVO.setBirthday("5월4일");
		introduceVO.setPhone("010-4942-8361");
		introduceVO.setGirlFriend("유정");
		request.setAttribute("introduce", introduceVO );
		
		List<IntroduceVO> introduceList = new ArrayList<IntroduceVO>();
		introduceList.add(introduceVO);
		request.setAttribute("introduceList", introduceList);
		
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
