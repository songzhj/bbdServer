package com.bbd.dao;

import com.bbd.entity.Treasure;

public interface TreasureDao {
    int deleteByPrimaryKey(String id);

    int insert(Treasure record);

    int insertSelective(Treasure record);

    Treasure selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Treasure record);

    int updateByPrimaryKey(Treasure record);
}