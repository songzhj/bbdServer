package com.bbd.serviceImpl;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.BuyerDao;
import com.bbd.dao.OrderDao;
import com.bbd.dao.SellerDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.Buyer;
import com.bbd.entity.Order;
import com.bbd.entity.Seller;
import com.bbd.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private TreasureDao treasureDao;
	@Autowired
	private OrderDao orderDao;

	@Override
	public int regist(Seller newSeller, String code) {
		Buyer isExit1 = buyerDao.selectBuyerByPrimary(newSeller.getId());
		Seller isExit2 = sellerDao.selectSellerByPrimary(newSeller.getId());
		if (isExit1 != null || isExit2 != null) {
			return 2;
		}
		Seller seller = sellerDao.selectSellerByPrimary(newSeller.getEmail());
		if (seller == null) {
			return 0;
		}
		if (seller.getPwd().equals(code)) {
			if (newSeller.getUserPic() == null)
				newSeller.setUserPic("F://save/user_pic/dafult.jpg");
			sellerDao.insertSeller(newSeller);
			sellerDao.deleteForCode(seller.getId());
			return 1;
		}
		return 3;
	}

	@Override
	public String login(String id, String pwd) {
		Seller seller = sellerDao.selectSellerByPrimary(id);
		if (seller == null) {
			return "0";
		}
		if (seller.getState().equals("0")) {
			return "2"; // 状态"2"为未通过审核
		}
		if (seller.getPwd().equals(pwd)) {
			JSONStringer stringer = new JSONStringer();
			String json = stringer.object().key("id").value(seller.getId())
					.key("email").value(seller.getEmail()).key("birthday")
					.value(seller.getBirthday()).key("sex")
					.value(seller.getSex()).key("id_card")
					.value(seller.getIdCard()).key("phone")
					.value(seller.getPhone()).key("name")
					.value(seller.getName()).endObject().toString();
			return json;
		}
		return "0";
	}

	@Override
	public int changePwd(String id, String pwd, String newPwd) {
		Seller seller = sellerDao.selectSellerByPrimary(id);
		if (seller != null) {
			if (seller.getPwd().equals(pwd)) {
				seller.setPwd(newPwd);
				sellerDao.updatePwd(seller);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int updateInfo(HttpServletRequest request) {
		try {
			String id = (String)request.getSession().getAttribute("id");
			if (id == null) id = "null";
			Seller seller = sellerDao.selectSellerByPrimary(id);
			seller.setName(request.getParameter("name"));
			seller.setSex(request.getParameter("sex"));
			seller.setBirthday(Date.valueOf(request.getParameter("birthday")));
			seller.setPhone(request.getParameter("phone"));
			sellerDao.insertSeller(seller);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getIndo(String id) {
		Seller seller = sellerDao.selectSellerByPrimary(id);
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("user_head", "F://pic/default_head.jpg"); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		json.put("name", seller.getName());
		json.put("sex", seller.getSex());
		json.put("birthday", seller.getBirthday());
		json.put("phone", seller.getPhone());
		return json.toString();
	}

	@Override
	public String getOrder(String userId, String state) {
		List<Order> order = orderDao.selectByBuyerId(userId);
		for (Order o : order) { //筛选非此状态的订单
			if (!o.getState().equals(state)) {
				order.remove(o);
			}
		}
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Order o : order) {
			JSONObject json = new JSONObject();
			json.put("order_id", o.getId());
			json.put("user_id", o.getSellerId());
			json.put("t_id", o.gettId());
			json.put("t_name", treasureDao.selectByPrimaryKey(o.gettId()).getName());
			json.put("t_num", o.gettNum());
			json.put("price", o.getprice());
			json.put("state", o.getState());
			arr.put(json);
		}
		data.put("orders", arr);
		return data.toString();
	}
}
