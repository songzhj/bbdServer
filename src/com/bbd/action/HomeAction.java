package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbd.service.OrderService;
import com.bbd.serviceImpl.OrderServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class SellerHomeAction
 */
@WebServlet("/home_action")
public class HomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeAction() {
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
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String userType = request.getParameter("user_type");
		String data = orderServiceImpl.getOrderNumByType(id, userType );
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
