package com.bbd.dao;

import com.bbd.entity.Cart;

public interface CartDao {
    int insert(Cart record);

    int insertSelective(Cart record);
}