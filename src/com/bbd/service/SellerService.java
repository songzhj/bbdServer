package com.bbd.service;

import com.bbd.entity.Seller;

public interface SellerService {
	int regist(Seller seller, String code);
	String login(String id, String pwd);
}
