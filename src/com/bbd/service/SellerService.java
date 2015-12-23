package com.bbd.service;

import javax.servlet.http.HttpServletRequest;

import com.bbd.entity.Seller;

public interface SellerService {
	int regist(Seller seller, String code);
	String login(String id, String pwd);
	int changePwd(String id, String pwd, String newPwd);
	int updateInfo(HttpServletRequest request);
	String getIndo(String id);
	String getOrder(String userId, String state);
	boolean savePic(HttpServletRequest request, String id);
}
