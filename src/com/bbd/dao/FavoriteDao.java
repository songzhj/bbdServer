package com.bbd.dao;

import com.bbd.entity.Favorite;

public interface FavoriteDao {
    int insert(Favorite record);

    int insertSelective(Favorite record);
}