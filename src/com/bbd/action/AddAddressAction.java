package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.BuyerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class AddAddressAction
 */
@WebServlet("/add_address_action")
public class AddAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAddressAction() {
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

		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		int isSuccess = buyerServiceImpl.addAddress(request);
		returnData(isSuccess + "", response);
	}

	private void returnData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
