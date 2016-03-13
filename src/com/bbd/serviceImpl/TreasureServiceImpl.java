package com.bbd.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bbd.dao.SellerDao;
import com.bbd.dao.TIndexDao;
import com.bbd.dao.TPicDao;
import com.bbd.dao.TreasureDao;
import com.bbd.entity.TIndex;
import com.bbd.entity.TPic;
import com.bbd.entity.Treasure;
import com.bbd.service.TreasureService;

@Service
public class TreasureServiceImpl implements TreasureService {
	@Autowired
	TreasureDao treasureDao;
	@Autowired
	TIndexDao tIndexDao;
	@Autowired
	TPicDao tPicDao;
	@Autowired
	SellerDao sellerDao;

	@Override
	public int publishTreasure(String id, ArrayList<String> pics, HttpServletRequest request) {
		try {
			Treasure t = setTreasure(id, request);
			treasureDao.insert(t);
			setTreasureIndex(t, pics);
			setTreasurePicture(t, pics);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	private void setTreasurePicture(Treasure t, ArrayList<String> pics) {
		for (int i = 1; i < pics.size(); ++i) {
			TPic tpic = new TPic();
			tpic.settId(t.getId());
			tpic.setPicUrl(pics.get(i));
			tPicDao.insert(tpic);
		}
	}

	private void setTreasureIndex(Treasure t, ArrayList<String> pics) {
		if (pics.size() != 0) {
			TIndex index = new TIndex();
			index.settId(t.getId());
			index.settName(t.getName());
			index.setPicUrl(pics.get(0));
			tIndexDao.insert(index);
		}
	}

	private Treasure setTreasure(String id, HttpServletRequest request) {
		Treasure t = new Treasure();
		t.setName(request.getParameter("name"));
		t.setSex(request.getParameter("sex"));
		t.setBrand(request.getParameter("brand"));
		t.setColor(request.getParameter("color"));
		t.setSize(request.getParameter("size"));
		t.setPrice(Double.parseDouble(request.getParameter("price")));
		t.setNum(new BigDecimal(request.getParameter("num")));
		t.setDescribe(request.getParameter("describe"));
		String tid = getTreasureId(t);
		t.setSellerId(id);
		t.setId(tid);
		return t;
	}

	private String getTreasureId(Treasure t) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String nameHash = t.getName().hashCode() + "";
		String tid = f.format(Calendar.getInstance().getTime())
				+ nameHash.substring(nameHash.length() - 3);
		return tid;
	}

	@Override
	public int removeTreasure(String tid) {
		if (null != treasureDao.selectByPrimaryKey(tid)) {
			treasureDao.deleteByPrimaryKey(tid);
			tIndexDao.deleteByTid(tid);
			tPicDao.deleteByTid(tid);
			//TODO:删除图片服务器的图片
			return 1;
		}
		return 0;
	}

	@Override
	public String searchTreasure(String context) {
		List<TIndex> index = searchIndexs(context);
		if (index == null)
			return "0";
		JSONObject data = treasureToJson(index);
		System.out.println(data.toString());
		return data.toString();
	}

	private JSONObject treasureToJson(List<TIndex> index) {
		JSONArray jsArr = new JSONArray();
		for (TIndex t : index) {
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
			for (int j = 0; j < index.size(); ++j) {
				if (index.get(j).gettName().indexOf(conditions[i]) == -1) {
					index.remove(j);
				}
			}
		}
		return index;
	}

	@Override
	public String getDetails(String id) {
		Treasure treasure = treasureDao.selectByPrimaryKey(id);
		JSONObject data = new JSONObject();
		if (treasure == null) {
			data.put("state", "empty");
			return data.toString();
		}
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
		data.put("phone", sellerDao.selectSellerByPrimary(treasure.getSellerId()).getPhone());
		data.put("sex", treasure.getSex());
		data.put("num", treasure.getNum());
		data.put("describe", treasure.getDescribe());
		System.out.println(treasure.getDescribe());
		data.put("brand", treasure.getBrand());
		data.put("color", getJsonArr(treasure.getColor()));
		data.put("size", getJsonArr(treasure.getSize()));
		return data;
	}

	private JSONArray getJsonArr(String string) {
		JSONArray arr = new JSONArray();
		String[] sp = string.split(",|，");
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

	@Override
	public ArrayList<String> getPics(String id, HttpServletRequest request) throws IllegalStateException, IOException {
		
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request); 
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Set<Entry<String, MultipartFile>> set = fileMap.entrySet();
		Iterator<Entry<String, MultipartFile>> it = set.iterator();
		ArrayList<String> pics = new ArrayList<String>();
		int i = 0;
		while (it.hasNext()) {
			Entry<String, MultipartFile> entry = (Entry<String, MultipartFile>) it.next();
			String key = (String) entry.getKey();
			
			MultipartFile file = (MultipartFile) entry.getValue();
			File originFile = new File("F:/code/php", key);
			file.transferTo(originFile);
			String fileName = "";
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
			fileName = f.format(Calendar.getInstance().getTime()) + id + i++ + ".jpg";
			pics.add(fileName);
			File newFile = new File("F:/code/php/" + fileName);
			originFile.renameTo(newFile);
		}
		return pics;
	}

}
