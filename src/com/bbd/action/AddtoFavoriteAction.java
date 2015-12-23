package com.bbd.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bbd.service.FavoriteService;
import com.bbd.serviceImpl.FavoriteServiceImpl;
import com.bbd.util.SpringContextUtil;

/**
 * Servlet implementation class AddtoFavoriteAction
 */
@WebServlet("/addto_favorite_action")
public class AddtoFavoriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoFavoriteAction() {
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

		FavoriteService favoriteServiceImpl = (FavoriteServiceImpl) SpringContextUtil
				.getBean("favoriteServiceImpl");
		String tId = request.getParameter("t_id");
		String uId = (String) request.getSession().getAttribute("id");
		if (uId == null) {
			JSONObject json = new JSONObject();
			json.put("user_id", "null");
			returnData(json.toString(), response);
			return;
		}
		String isSuccess = "" + favoriteServiceImpl.add(tId, uId);
		returnData(isSuccess, response);
	}

	private void returnData(String data, HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
}
