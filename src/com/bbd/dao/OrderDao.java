package com.bbd.dao;

import java.util.List;

import com.bbd.entity.Order;

public interface OrderDao {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);
    
    List<Order> selectByBuyerId(String buyerId);
    
    List<Order> selectBySellerId(String sellerId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}