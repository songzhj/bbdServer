package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.CommonService;
import com.bbd.serviceImpl.CommonServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class RetrievePassword
 */
@WebServlet("/retrieve_password")
public class RetrievePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetrievePassword() {
		super();
		// TODO Auto-generated constructor stub
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
		
		CommonService commonServiceImpl = (CommonServiceImpl) SpringContextUtil
				.getBean("commonServiceImpl");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		try {
			Integer data = commonServiceImpl.retrievePassword(id, email);
			returnData(data.toString(), response);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
	}
}
