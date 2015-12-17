package com.bbd.dao;

import java.util.List;

import com.bbd.entity.TPic;

public interface TPicDao {
    int insert(TPic record);

    int insertSelective(TPic record);
    
    List<TPic> selectByTId(String id);
}