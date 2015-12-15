package com.bbd.dao;

import com.bbd.entity.Address;

public interface AddressDao {
    int deleteByPrimaryKey(String addrId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(String addrId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}