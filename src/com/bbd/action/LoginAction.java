package com.bbd.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bbd.service.BuyerService;
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login_action")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		returnData("Can not access !", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		InputStream in = request.getInputStream();
//		int ch = 0;String d = "";
//		while ((ch = in.read()) != -1) {
//			d += (char)ch;
//		}
//		System.out.println(d);

//        Map<String, String[]> map = request.getParameterMap();  
//        Set<Entry<String, String[]>> set = map.entrySet();  
//        Iterator<Entry<String, String[]>> it = set.iterator();  
//        while (it.hasNext()) {  
//            Entry<String, String[]> entry = it.next();  
//  
//            System.out.print("KEY:"+entry.getKey() + "---");  
//            for (String i : entry.getValue()) {  
//                System.out.print(i + "  ");  
//            } 
//            System.out.println();
//        }  
		
//		JSONObject json = new JSONObject(request.getParameter("ddd"));

		String userType = request.getParameter("user_type");
		if (userType == null) returnData("0", response);
		switch (userType) {
		case "buyer":
			buyerLogin(request, response);
			break;
		case "seller":
			sellerLogin(request, response);
			break;
		case "admin":
			adminLogin(request, response);
			break;
		default:
			returnData("0", response);
			break;
		}
	}

	private void adminLogin(HttpServletRequest request,
			HttpServletResponse response) {

	}

	private void sellerLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		SellerService sellerService = (SellerServiceImpl) SpringContextUtil
				.getBean("sellerServiceImpl");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String data = sellerService.login(id, pwd);
		returnData(data, response);
	}

	private void buyerLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		BuyerService buyerService = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String data = buyerService.login(id, pwd);
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
