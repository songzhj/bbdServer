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
import com.bbd.service.SellerService;
import com.bbd.serviceImpl.BuyerServiceImpl;
import com.bbd.serviceImpl.SellerServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class ChangePwd
 */
@WebServlet("/change_pwd_action")
public class ChangePwdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			JSONObject json = new JSONObject();
			json.put("user_id", "null");
			returnData(json.toString(), response);
			return;
		}
		String userType = (String)request.getSession().getAttribute("userType");
		switch (userType) {
		case "buyer":
			changeBuyerPwd(request, response);
			break;
		case "seller":
			changeSellerPwd(request, response);
			break;
		default:
			returnData(userType, response);
			break;
		}
	}

	private void changeSellerPwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		SellerService sellerServiceImpl = (SellerServiceImpl) SpringContextUtil.getBean("sellerServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("new_pwd");
		int data = sellerServiceImpl.changePwd(id, pwd, newPwd);
		returnData(data + "", response);
	}

	private void changeBuyerPwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		BuyerService buyerServiceImpl = (BuyerServiceImpl) SpringContextUtil.getBean("buyerServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("new_pwd");
		int data = buyerServiceImpl.changePwd(id, pwd, newPwd);
		returnData(data + "", response);
	}

	private void returnData(String data, HttpServletResponse response) throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}

}
