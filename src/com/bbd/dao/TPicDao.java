package com.bbd.dao;

import com.bbd.entity.TPic;

public interface TPicDao {
    int insert(TPic record);

    int insertSelective(TPic record);
}