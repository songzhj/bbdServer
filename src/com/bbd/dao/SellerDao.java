package com.bbd.dao;

import com.bbd.entity.Seller;

public interface SellerDao {
	Seller selectSellerByPrimary(String id);
	int insertSeller(Seller seller);
	void updateSeller(Seller seller);
	Seller selectForCode(String id);
	int insertForCode(Seller seller);
	int deleteForCode(String id);
	void updateForCode(Seller seller);
	void updateState(Seller seller);
}
