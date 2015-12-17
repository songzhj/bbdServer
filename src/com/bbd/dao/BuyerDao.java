package com.bbd.dao;

import com.bbd.entity.Buyer;

public interface BuyerDao {
	Buyer selectBuyerByPrimary(String id);
	int insertBuyer(Buyer buyer);
	void updateBuyer(Buyer buyer);
	Buyer selectForCode(String id);
	int insertForCode(Buyer buyer);
	int deleteForCode(String id);
	void updateForCode(Buyer buyer);
	void updatePwd(Buyer buyer);
}
