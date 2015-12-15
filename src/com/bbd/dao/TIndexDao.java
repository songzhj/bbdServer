package com.bbd.dao;

import com.bbd.entity.TIndex;

public interface TIndexDao {
    int insert(TIndex record);

    int insertSelective(TIndex record);
}