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
 * Servlet implementation class DelAddress
 */
@WebServlet("/del_address_action")
public class DelAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelAddressAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		String aid = request.getParameter("aid");
		int isSuccess = buyerServiceImpl.delAddress(aid);
		returnData(isSuccess + "", response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}	
}
