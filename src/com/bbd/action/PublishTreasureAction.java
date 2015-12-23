package com.bbd.action;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bbd.service.TreasureService;
import com.bbd.serviceImpl.TreasureServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class PublishTreasure
 */
@WebServlet("/publish_treasure_action")
public class PublishTreasureAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublishTreasureAction() {
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
		
		TreasureService treasureServiceImpl = (TreasureServiceImpl) SpringContextUtil
				.getBean("treasureServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			JSONObject json = new JSONObject();
			json.put("user_id", "null");
			returnData(json.toString(), response);
			return;
		}
		ArrayList<String> pics = treasureServiceImpl.getPics(id, request);
		int isSuccess = treasureServiceImpl.publishTreasure(id, pics, request);
		if (isSuccess == 1) {
			response.sendRedirect("http://bbd.songzhj.com/selling_treasure.html");
		} else {
			response.sendRedirect("error.html");
		}
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		System.out.println(data);
		out.close();
	}	
}
