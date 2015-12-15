package com.bbd.serviceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.TIndexDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.TPic;
import com.bbd.entity.Treasure;
import com.bbd.service.TreasureService;

@Service
public class TreasureServiceImpl implements TreasureService{
	@Autowired
	TreasureDao treasureDao;
	@Autowired
	TIndexDao tIndexDao;
	@Autowired
	TPic tPic;

	@Override
	public int publishTreasure(String data) {
		try {
			JSONObject jsonObj = new JSONObject(data);
			Treasure t = new Treasure();
			t.setName(jsonObj.getString("name"));
			t.setSex(jsonObj.getString("sex"));
			t.setBrand(jsonObj.getString("brand"));
			t.setColor(jsonObj.getString("color"));
			t.setPrice(jsonObj.getDouble("price"));
			t.setSellerId(jsonObj.getString("seller_id"));
			t.setNum(jsonObj.getBigDecimal("num"));
			t.setDescribe(jsonObj.getString("describe"));
			t.setSize(jsonObj.getString("size"));
			JSONArray pics = jsonObj.getJSONArray("pic_url");
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
}
