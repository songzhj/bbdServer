package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.TreasureService;
import com.bbd.serviceImpl.TreasureServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class PublishTreasure
 */
@WebServlet("/publish_treasure")
public class PublishTreasure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublishTreasure() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		TreasureService treasureServiceImpl = (TreasureServiceImpl) SpringContextUtil
				.getBean("treasureServiceImpl");
		String data = request.getParameter("data");
		int isSuccess = treasureServiceImpl.publishTreasure(data);
		retunData("" + isSuccess, response);
	}

	private void retunData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
