package com.bbd.dao;

import java.util.List;

import com.bbd.entity.Cart;

public interface CartDao {
    int insert(Cart record);

    int insertSelective(Cart record);
    
    Cart selectByCart(Cart record);
    
    int updateByCart(Cart record);
    
    int deleteByCart(Cart record);

	List<Cart> selectById(String id);
}