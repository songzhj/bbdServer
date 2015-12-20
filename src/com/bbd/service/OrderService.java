package com.bbd.service;

public interface OrderService {
	int changeState(String id, String state);

	String getOrderNumByType(String id, String userType);
}
