package com.bbd.service;

import com.bbd.entity.Buyer;

public interface BuyerService {
	int regist(Buyer buyer, String code);
	String login(String id, String pwd);
}
