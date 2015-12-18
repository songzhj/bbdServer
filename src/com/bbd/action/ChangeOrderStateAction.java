package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.OrderService;
import com.bbd.serviceImpl.OrderServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class ChangeOrderStateAction
 */
@WebServlet("/change_order_state_action")
public class ChangeOrderStateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeOrderStateAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		returnData("Can not access !", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		OrderService orderServiceImpl = (OrderServiceImpl) SpringContextUtil
				.getBean("orderServiceImpl");
		String orderId = request.getParameter("order_id");
		String state = request.getParameter("state");
		String isSuccess = "" + orderServiceImpl.changeState(orderId, state);
		returnData(isSuccess, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
