package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.CartService;
import com.bbd.serviceImpl.CartServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class AddtoCartAction
 */
@WebServlet("/edit_cart_action")
public class EditCartAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCartAction() {
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

		CartService cartServiceImpl = (CartServiceImpl) SpringContextUtil
				.getBean("cartServiceImpl");
		String tId = request.getParameter("t_id");
		String uId = request.getParameter("u_id");
		String num = request.getParameter("num");
		String isSuccess = "" + cartServiceImpl.editNum(tId, uId, num);
		returnData(isSuccess, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
