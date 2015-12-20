package com.bbd.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.OrderDao;
import com.bbd.entity.Order;
import com.bbd.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;

	@Override
	public int changeState(String id, String state) {
		Order order = new Order();
		order.setId(id);
		order.setState(state);
		return orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public String getOrderNumByType(String id, String userType) {
		if (userType.equals("seller")) {
			return getSellerOrderByType(id);
		} else if (userType.equals("buyer")) {
			return getBuyerOrderByType(id);
		}
		return "0";
	}

	private String getBuyerOrderByType(String id) {
		return null;
	}

	private String getSellerOrderByType(String id) {
		List<Order> list = orderDao.selectBySellerId(id);
		Integer waitPay = 0;
		Integer waitOut = 0;
		Integer alreadyOut = 0;
		Integer refund = 0;
		for (Order o : list) {
			switch (o.getState()) {
			case "待付款":
				waitPay++;
				break;
			case "待发货":
				waitOut++;
				break;
			case "已发货":
				alreadyOut++;
				break;
			case "退款中":
				refund++;
				break;
			default:
				break;
			}
		}
		JSONObject data = new JSONObject();
		data.put("wait_pay", waitPay);
		data.put("wait_out", waitOut);
		data.put("already_out", alreadyOut);
		data.put("refund", refund);
		data.put("user_id", id);
		return data.toString();
	}

}
