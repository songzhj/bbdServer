package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.BuyerService;
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class UpdateUserInfoAction
 */
@WebServlet("/update_user_info_action")
public class UpdateUserInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfoAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		returnData("Can not access !", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = request.getParameter("user_type");
		switch (userType) {
		case "buyer":
			updateBuyerInfo(request, response);
			break;
		case "seller":
			updateSellerInfo(request, response);
			break;
		default:
			returnData("0", response);
			break;
		}
	}

	private void updateBuyerInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		int isSuccess = buyerServiceImpl.updateInfo(request);
		returnData(isSuccess + "", response);
	}

	private void updateSellerInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		SellerService sellerService = (SellerServiceImpl) SpringContextUtil
				.getBean("sellerServiceImpl");
		int isSuccess = sellerService.updateInfo(request);
		returnData(isSuccess + "", response);
	}

	private void returnData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
