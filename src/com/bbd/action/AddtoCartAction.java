package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bbd.service.CartService;
import com.bbd.serviceImpl.CartServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class AddtoCartAction
 */
@WebServlet("/addto_cart_action")
public class AddtoCartAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoCartAction() {
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
		String uId = (String) request.getSession().getAttribute("id");
		if (uId == null) {
			JSONObject json = new JSONObject();
			json.put("user_id", "null");
			returnData(json.toString(), response);
			return;
		}
		String isSuccess = "" + cartServiceImpl.add(tId, uId);
		returnData(isSuccess, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
