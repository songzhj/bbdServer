package com.bbd.service;

import javax.servlet.http.HttpServletRequest;






import com.bbd.entity.Buyer;

public interface BuyerService {
	int regist(Buyer buyer, String code);
	String login(String id, String pwd);
	int changePwd(String id, String pwd, String newPwd);
	int booking(String data);
	int updateInfo(HttpServletRequest request);
	String getInfo(String id);
	int addAddress(HttpServletRequest request);
	String getAddress(String id);
	String getOrder(String userId, String state);
}
