package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbd.service.TreasureService;
import com.bbd.serviceImpl.TreasureServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class GetTreasureAction
 */
@WebServlet("/get_treasure_action")
public class GetTreasureAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTreasureAction() {
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
		TreasureService treasureServiceImpl = (TreasureServiceImpl) SpringContextUtil
				.getBean("treasureServiceImpl");
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) id = "null";
		String data = treasureServiceImpl.getTreasures(id);
		returnData(data, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
