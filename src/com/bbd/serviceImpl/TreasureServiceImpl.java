package com.bbd.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.TIndexDao;
import com.bbd.dao.TPicDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.TIndex;
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
	TPicDao tPicDao;

	@Override
	public int publishTreasure(String data) {
		try {
			JSONObject jsonObj = new JSONObject(data);
			Treasure t = setTreasure(jsonObj);
			treasureDao.insert(t);
			
			JSONArray pics = jsonObj.getJSONArray("pic_url");
			
			setTreasureIndex(t, pics);
			
			setTreasurePicture(t, pics);
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	private void setTreasurePicture(Treasure t, JSONArray pics) {
		for (int i = 1; i < pics.length(); ++i) {
			TPic tpic = new TPic();
			tpic.settId(t.getId());
			tpic.setPicUrl(pics.getString(i));
			tPicDao.insert(tpic);
		}
	}

	private void setTreasureIndex(Treasure t, JSONArray pics) {
		if (pics.length() != 0) {
			TIndex index = new TIndex();
			index.settId(t.getId());
			index.settName(t.getName());
			index.setPicUrl(pics.getString(0));
			tIndexDao.insert(index);
		}
	}

	private Treasure setTreasure(JSONObject jsonObj) {
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
		String tid = getTreasureId(t);
		t.setId(tid);
		return t;
	}

	private String getTreasureId(Treasure t) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String nameHash = t.getName().hashCode() + "";
		String tid = f.format(Calendar.getInstance().getTime()) + nameHash.substring(nameHash.length() - 3);
		return tid;
	}

	@Override
	public int removeTreasure(String tid) {
		if (null != treasureDao.selectByPrimaryKey(tid)) {
			treasureDao.deleteByPrimaryKey(tid);
			return 1;
		}
		return 0;
	}

	@Override
	public String searchTreasure(String context) {
		List<TIndex> index = searchIndexs(context);
		if (index == null) return "0";	
		JSONObject data = treasureToJson(index);
		return data.toString();
	}

	private JSONObject treasureToJson(List<TIndex> index) {
		JSONArray jsArr = new JSONArray();
		for(TIndex t : index) {
			JSONObject jsObj = new JSONObject();
			jsObj.put("id", t.gettId());
			jsObj.put("name", t.gettName());
			jsObj.put("t_index", t.getPicUrl());
			jsArr.put(jsObj);
		}
		JSONObject data = new JSONObject();
		data.put("treasure", jsArr);
		return data;
	}

	private List<TIndex> searchIndexs(String context) {
		String[] conditions = context.split(" ");
		if (conditions.length == 0) {
			return null;
		}
		List<TIndex> index = tIndexDao.selectByName(conditions[0]);
		for (int i = 1; i < conditions.length; ++i) {
			for (TIndex t : index) {
				if (t.gettName().indexOf(conditions[i]) == -1) {
					index.remove(t);
				}
			}
		}
		return index;
	}

	@Override
	public String getDetails(String id) {
		Treasure treasure = treasureDao.selectByPrimaryKey(id);
		JSONObject data = new JSONObject();
		data = getTreasureDetails(treasure, data);
		List<TPic> pics = tPicDao.selectByTId(treasure.getId());
		data.put("pic", getTreasurePictures(pics));
		return data.toString();
	}

	private JSONArray getTreasurePictures(List<TPic> pics) {
		JSONArray arr = new JSONArray();
		for (TPic p : pics) {
			arr.put(p.getPicUrl());
		}
		return arr;
	}

	private JSONObject getTreasureDetails(Treasure treasure, JSONObject data) {
		data.put("id", treasure.getId());
		data.put("name", treasure.getName());
		data.put("price", treasure.getPrice());
		data.put("seller_id", treasure.getSellerId());
		data.put("sex", treasure.getSex());
		data.put("num", treasure.getNum());
		data.put("describe", treasure.getDescribe());
		data.put("brand", getJsonArr(treasure.getBrand()));
		data.put("color", getJsonArr(treasure.getColor()));
		data.put("size", getJsonArr(treasure.getSize()));
		return data;
	}

	private JSONArray getJsonArr(String string) {
		JSONArray arr = new JSONArray();
		String[] sp = string.split(",");
		for (String s : sp) {
			arr.put(s);
		}
		return arr;
	}

	@Override
	public String getTreasures(String sellerId) {
		List<Treasure> list = treasureDao.selectBySellerId(sellerId);
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Treasure t : list) {
			JSONObject o = new JSONObject();
			o.put("t_id", t.getId());
			o.put("name", t.getName());
			o.put("price", t.getPrice());
			o.put("t_pic", tIndexDao.selectById(t.getId()).getPicUrl());
			o.put("num", t.getNum());
			arr.put(o);
		}
		data.put("treasures", arr);
		System.out.println(data.toString());
		return data.toString();
	}
	
}
