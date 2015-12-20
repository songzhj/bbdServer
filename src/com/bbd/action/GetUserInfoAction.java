package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbd.service.BuyerService;
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class GetUserInfoAction
 */
@WebServlet("/get_user_info_action")
public class GetUserInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserInfoAction() {
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

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		if (userType == null) return;
		switch (userType) {
		case "buyer":
			getBuyerInfo(request, response);
			break;
		case "seller":
			getSellerInfo(request, response);
			break;
		default:
			returnData("0", response);
			break;
		}
	}

	private void getBuyerInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) return;
		String data = buyerServiceImpl.getInfo(id);
		returnData(data, response);
	}

	private void getSellerInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		SellerService sellerService = (SellerServiceImpl) SpringContextUtil
				.getBean("sellerServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) return;
		String data = sellerService.getIndo(id); //info, 手误了....
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
