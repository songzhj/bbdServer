package com.bbd.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.RegisterCodeService;
import com.bbd.serviceImpl.RegisterCodeServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class RegisterCodeAction
 */
@WebServlet("/register_code_acion")
public class RegisterCodeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCodeAction() {
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
		RegisterCodeService registerCodeServiceImpl = (RegisterCodeServiceImpl) SpringContextUtil.getBean("registerCodeServiceImpl");
		String email = request.getParameter("email");
		try {
			registerCodeServiceImpl.sendCode(email);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
