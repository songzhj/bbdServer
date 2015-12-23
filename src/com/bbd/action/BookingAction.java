package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bbd.service.BuyerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class BookingAction
 */
@WebServlet("/booking_action")
public class BookingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingAction() {
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
		
		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		String data = request.getParameter("data");
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			JSONObject json = new JSONObject();
			json.put("user_id", "null");
			returnData(json.toString(), response);
			return;
		}
		int isSuccess = buyerServiceImpl.booking(data, id);
		returnData(isSuccess + "", response);
	}

	private void returnData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
