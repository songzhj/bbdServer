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
 * Servlet implementation class RemoveTreasure
 */
@WebServlet("/remove_treasure_action")
public class RemoveTreasureAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveTreasureAction() {
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
		String tid = request.getParameter("t_id");
		int isSuccess = treasureServiceImpl.removeTreasure(tid);
		returnData(isSuccess + "", response);
	}

	private void returnData(String isSuccess, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(isSuccess);
		out.close();
	}

}
