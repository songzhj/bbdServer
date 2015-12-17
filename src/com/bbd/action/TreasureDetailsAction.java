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
 * Servlet implementation class TreasureDetails
 */
@WebServlet("/treasure_details_action")
public class TreasureDetailsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TreasureDetailsAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		TreasureService treasureServiceImpl = (TreasureServiceImpl) SpringContextUtil
				.getBean("treasureServiceImpl");
		String id = request.getParameter("id");
		String data = treasureServiceImpl.getDetails(id);
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
