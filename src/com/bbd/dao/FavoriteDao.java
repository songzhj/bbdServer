package com.bbd.dao;

import java.util.List;

import com.bbd.entity.Favorite;

public interface FavoriteDao {
    int insert(Favorite record);

    int insertSelective(Favorite record);

	Favorite selectByFavorite(Favorite favorite);

	void deleteByFavorite(Favorite favorite);

	List<Favorite> selectById(String id);

}