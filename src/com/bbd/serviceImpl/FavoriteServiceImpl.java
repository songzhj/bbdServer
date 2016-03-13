package com.bbd.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.FavoriteDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.Cart;
import com.bbd.entity.Favorite;
import com.bbd.entity.Treasure;
import com.bbd.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	FavoriteDao favoriteDao;
	@Autowired
	TreasureDao treasureDao;

	@Override
	public int add(String tId, String uId) {
		Favorite favorite = new Favorite();
		favorite.setId(uId);
		favorite.setTreasureId(tId);
		favorite = favoriteDao.selectByFavorite(favorite);
		if (favorite == null) {
			favorite = new Favorite();
			favorite.setId(uId);
			favorite.setTreasureId(tId);
			favoriteDao.insert(favorite);
			return 1;
		}
		return 0;
	}

	@Override
	public int del(String tId, String uId) {
		Favorite favorite = new Favorite();
		favorite.setId(uId);
		favorite.setTreasureId(tId);
		favorite = favoriteDao.selectByFavorite(favorite);
		if (favorite == null) return 0;
		favoriteDao.deleteByFavorite(favorite);
		return 1;
	}

	@Override
	public String getFavorites(String id) {
		List<Favorite> list = favoriteDao.selectById(id);
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Favorite c : list) {
			JSONObject o = new JSONObject();
			Treasure treasure = treasureDao.selectByPrimaryKey(c.getTreasureId());
			if (treasure != null) {
				o.put("t_id", c.getTreasureId());
				o.put("seller_id", treasure.getSellerId());
				o.put("name", treasure.getName());
				arr.put(o);
			}
		}
		data.put("favorites", arr);
		return data.toString();
	}

}
