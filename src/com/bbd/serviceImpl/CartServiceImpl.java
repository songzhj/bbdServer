package com.bbd.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.dao.CartDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.Cart;
import com.bbd.entity.Treasure;
import com.bbd.service.CartService;

public class CartServiceImpl implements CartService {
	@Autowired
	CartDao cartDao;
	@Autowired
	TreasureDao treasureDao;

	/**
	 * 向购物车添加商品,修改数量
	 */
	@Override
	public int add(String tId, String uId) {
		Cart cart = new Cart();
		cart.setId(uId);
		cart.setTreasureId(tId);
		cart = cartDao.selectByCart(cart);
		if (cart == null) {
			cart = new Cart();
			cart.setId(uId);
			cart.setTreasureId(tId);
			cart.setTreasureNum(new BigDecimal(1));
			cartDao.insert(cart);
			return 1;
		} else {
			cart.setTreasureNum(cart.getTreasureNum().add(new BigDecimal(1)));
			cartDao.updateByCart(cart);
			return 1;
		}
	}

	/**
	 * 删除购物车中的商品
	 */
	@Override
	public int del(String tId, String uId) {
		Cart cart = new Cart();
		cart.setId(uId);
		cart.setTreasureId(tId);
		cart = cartDao.selectByCart(cart);
		if (cart == null) return 0;
		cartDao.deleteByCart(cart);
		return 1;
	}
	
	@Override
	public int editNum(String tId, String uId, String num) {
		try {
			Cart cart = new Cart();
			cart.setId(uId);
			cart.setTreasureId(tId);
			cart.setTreasureNum(new BigDecimal(num));
			cartDao.updateByCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public String getCarts(String id) {
		List<Cart> list = cartDao.selectById(id);
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Cart c : list) {
			JSONObject o = new JSONObject();
			Treasure treasure = treasureDao.selectByPrimaryKey(c.getTreasureId());
			o.put("t_id", c.getTreasureId());
			o.put("seller_id", treasure.getSellerId());
			o.put("name", treasure.getName());
			o.put("num", c.getTreasureNum());
			o.put("price", treasure.getPrice());
			arr.put(o);
		}
		data.put("carts", arr);
		return data.toString();
	}


}
