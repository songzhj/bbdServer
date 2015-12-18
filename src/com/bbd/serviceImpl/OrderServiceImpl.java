package com.bbd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.dao.OrderDao;
import com.bbd.entity.Order;
import com.bbd.service.OrderService;

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

}
