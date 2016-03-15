package com.bbd.dao;

import java.util.List;

import com.bbd.entity.TIndex;

public interface TIndexDao {
    int insert(TIndex record);

    int insertSelective(TIndex record);
    
    List<TIndex> selectByName(String tName);
    
    TIndex selectById(String id);

	void deleteByTid(String tid);
}