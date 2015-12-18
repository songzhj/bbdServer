package com.bbd.service;

public interface CartService {
	int add(String tId, String uId);

	int del(String tId, String uId);

	String getCarts(String id);

	int editNum(String tId, String uId, String num);
}
