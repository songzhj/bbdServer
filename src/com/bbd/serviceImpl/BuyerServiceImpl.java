package com.bbd.serviceImpl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.AddressDao;
import com.bbd.dao.BuyerDao;
import com.bbd.dao.OrderDao;
import com.bbd.dao.SellerDao;
import com.bbd.dao.TIndexDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.Address;
import com.bbd.entity.Buyer;
import com.bbd.entity.Order;
import com.bbd.entity.Seller;
import com.bbd.entity.Treasure;
import com.bbd.service.BuyerService;
import com.sun.org.apache.xpath.internal.operations.Or;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private TreasureDao treasureDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private TIndexDao tindexDao;

	@Override
	public int regist(Buyer newBuyer, String code) {
		Buyer isExit1 = buyerDao.selectBuyerByPrimary(newBuyer.getId());
		Seller isExit2 = sellerDao.selectSellerByPrimary(newBuyer.getId());
		if (isExit1 != null || isExit2 != null) {
			return 2;
		}
		Buyer buyer = buyerDao.selectBuyerByPrimary(newBuyer.getEmail());
		if (buyer == null) {
			return 0;
		}
		if (buyer.getPwd().equals(code)) {
			if (newBuyer.getUserPic() == null)
				newBuyer.setUserPic("F://save/user_pic/dafult.jpg");
			buyerDao.insertBuyer(newBuyer);
			buyerDao.deleteForCode(buyer.getId());
			return 1;
		}
		return 3;
	}

	@Override
	public String login(String id, String pwd) {
		Buyer buyer = buyerDao.selectBuyerByPrimary(id);
		if (buyer == null) {
			return "0";
		}
		if (buyer.getPwd().equals(pwd)) {
			JSONStringer stringer = new JSONStringer();
			String json = stringer.object().key("id").value(buyer.getId())
					.key("email").value(buyer.getEmail()).key("birthday")
					.value(buyer.getBirthday()).key("sex")
					.value(buyer.getSex()).key("name").value(buyer.getName())
					.endObject().toString();
			return json;
		}
		return "0";
	}

	@Override
	public int changePwd(String id, String pwd, String newPwd) {
		Buyer buyer = buyerDao.selectBuyerByPrimary(id);
		if (buyer != null) {
			if (buyer.getPwd().equals(pwd)) {
				System.out.println(pwd + newPwd);
				buyer.setPwd(newPwd);
				buyerDao.updatePwd(buyer);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int booking(String data, String id) {
		JSONObject json = new JSONObject(data);
		JSONArray arr = json.getJSONArray("data");
		for (int i = 0; i < arr.length(); ++i) { // 一组订单
			JSONObject jsonObj = (JSONObject) arr.get(i);
			int r = booking(jsonObj, id);
			if (r == 0) {
				return 0;
			}
		}
		return 1;
	}

	/**
	 * 生成每个订单
	 * 
	 * @param jsonObj
	 * @return
	 */
	private int booking(JSONObject jsonObj, String id) {
		Order order = new Order();
		order = setOrder(jsonObj, order, id);
		if (order == null)
			return 0;
		orderDao.insert(order);
		return 1;
	}

	/**
	 * 根据商品信息和买家信息生成订单
	 * 
	 * @param jsonObj
	 * @param order
	 * @return
	 */
	private Order setOrder(JSONObject jsonObj, Order order, String id) {
		order.setBuyerId(id);
		order.settId(jsonObj.getString("t_id"));
		order.settNum(jsonObj.getBigDecimal("t_num"));
		order.setAddrId(jsonObj.getString("addr_id"));
		Treasure treasure = treasureDao.selectByPrimaryKey(order.gettId());
		if (treasure == null)
			return null;
		order.setSellerId(treasure.getSellerId());
		order.setprice(order.gettNum().doubleValue() * treasure.getPrice());
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		order.setMakeTime(Date.valueOf(f.format(Calendar.getInstance()
				.getTime())));
		order.setState("待付款");
		order.setId(getOrderId());
		return order;
	}

	/**
	 * 通过当前时间和随机数获得订单编号.
	 * 
	 * @return
	 */
	private String getOrderId() {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = f.format(Calendar.getInstance().getTime());
		Random ran = new Random();
		String s = "";
		for (int i = 0; i < 3; ++i) {
			s += ran.nextInt(10);
		}
		return date + s;
	}

	@Override
	public int updateInfo(HttpServletRequest request) {
		try {
			String id = (String)request.getSession().getAttribute("id");
			if (id == null) id = "null";
			Buyer buyer = buyerDao.selectBuyerByPrimary(id);
			buyer.setName(request.getParameter("name"));
			System.out.println(request.getParameter("sex"));
			buyer.setSex(request.getParameter("sex"));
			buyer.setBirthday(Date.valueOf(request.getParameter("birthday")));
			buyerDao.updateBuyer(buyer);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addAddress(HttpServletRequest request) {
		try {
			Address address = new Address();
			address.setAddr(request.getParameter("province") + ","
					+ request.getParameter("address") + ","
					+ request.getParameter("name"));
			address.setPhone(request.getParameter("phone"));
			address.setSellerId(request.getParameter("id"));
			List<Address> list = addressDao.selectByBuyerId(address.getSellerId());
			address.setAddrId(address.getSellerId() + list.size());
			addressDao.insert(address);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取买家信息
	 * @param id
	 * @return
	 */
	@Override
	public String getInfo(String id) {
		Buyer buyer = buyerDao.selectBuyerByPrimary(id);
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("user_head", "F://pic/default_head.jpg"); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		json.put("name", buyer.getName());
		json.put("sex", buyer.getSex());
		json.put("birthday", buyer.getBirthday());
		return json.toString();
	}

	/**
	 * 获取收货地址
	 * @param id
	 * @return
	 */
	@Override
	public String getAddress(String id) {
		List<Address> list = addressDao.selectByBuyerId(id);
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Address a : list) {
			JSONObject o = new JSONObject();
			o.put("addr_id", a.getAddrId());
			o.put("phone", a.getPhone());
			String[] pan = a.getAddr().split(",");
			o.put("province", pan[0]);
			o.put("address", pan[1]);
			o.put("name", pan[2]);
			arr.put(o);
		}
		data.put("addresses", arr);
		return data.toString();
	}


	/**
	 *获取订单
	 */
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
			json.put("user_id", o.getBuyerId());
			json.put("t_id", o.gettId());
			json.put("t_name", treasureDao.selectByPrimaryKey(o.gettId()).getName());
			json.put("t_num", o.gettNum());
			json.put("price", o.getprice());
			json.put("state", o.getState());
			json.put("pic", tindexDao.selectById(o.getId()).getPicUrl());
			arr.put(json);
		}
		data.put("orders", arr);
		return data.toString();
	}

}
