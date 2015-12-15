package com.bbd.action;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.entity.Buyer;
import com.bbd.entity.Seller;
import com.bbd.service.BuyerService;
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class regesterAction
 */
@WebServlet("/register_action")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterAction() {
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
		String userType = request.getParameter("user_type");
		switch (userType) {
		case "buyer":
			buyerRegist(request, response);
			break;
		case "seller":
			sellerRegist(request, response);
			break;
		default:
			returnData("0", response);
			break;
		}
	}

	private void sellerRegist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		SellerService sellerService = (SellerServiceImpl) SpringContextUtil
				.getBean("sellerServiceImpl");
		Seller seller = new Seller();
		seller = setSeller(request, seller);
		String code = request.getParameter("code");
		Integer data = sellerService.regist(seller, code);
		returnData(data.toString(), response);
	}

	private Seller setSeller(HttpServletRequest request, Seller seller) {
		seller.setId(request.getParameter("id"));
		seller.setPwd(request.getParameter("pwd"));
		seller.setEmail(request.getParameter("email"));
		seller.setName(request.getParameter("name"));
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		seller.setRegisDate(Date.valueOf(f.format(Calendar.getInstance()
				.getTime())));
		seller.setBirthday(Date.valueOf(request.getParameter("birthday")));
		seller.setSex(request.getParameter("sex"));
		seller.setIdCard(request.getParameter("id_card"));
		seller.setPhone(request.getParameter("phone"));
		seller.setState("0");
		seller.setUserPic(request.getParameter("user_pic"));
		return seller;
	}

	private void buyerRegist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		BuyerService buyerService = (BuyerServiceImpl) SpringContextUtil
				.getBean("buyerServiceImpl");
		Buyer buyer = new Buyer();
		buyer = setBuyer(request, buyer);
		String code = request.getParameter("code");
		Integer data = buyerService.regist(buyer, code);
		returnData(data.toString(), response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
	}

	/**
	 * 设置Buyer实体
	 * 
	 * @param request
	 * @param buyer
	 */
	private Buyer setBuyer(HttpServletRequest request, Buyer buyer) {
		buyer.setId(request.getParameter("id"));
		buyer.setPwd(request.getParameter("pwd"));
		buyer.setEmail(request.getParameter("email"));
		buyer.setName(request.getParameter("name"));
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		buyer.setRegisDate(Date.valueOf(f.format(Calendar.getInstance()
				.getTime())));
		buyer.setBirthday(Date.valueOf(request.getParameter("birthday")));
		buyer.setSex(request.getParameter("sex"));
		buyer.setUserPic(request.getParameter("user_pic"));
		return buyer;
	}

}
