package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.bbd.service.BuyerService;
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class GetOrderAction
 */
@WebServlet("/get_order_action")
public class GetOrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		returnData("Can not access !", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String state = request.getParameter("order_state");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");
		String userType = (String) session.getAttribute("userType");
		switch (userType) {
		case "buyer":
			getBuyerOrder(userId, state, response);
			break;
		case "seller":
			getSellerOrder(userId, state, response);
			break;
		default:
			returnData("0", response);
			break;
		}
	}

	private void getBuyerOrder(String userId, String state, HttpServletResponse response) throws IOException {
		
		BuyerService buyerService = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		String data = buyerService.getOrder(userId, state);
		returnData(data, response);
	}

	private void getSellerOrder(String userId, String state, HttpServletResponse response) throws IOException {
		
		SellerService sellerService = (SellerServiceImpl) SpringContextUtil
				.getBean("sellerServiceImpl");
		String data = sellerService.getOrder(userId, state);
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
