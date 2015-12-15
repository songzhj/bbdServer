package com.bbd.serviceImpl;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.BuyerDao;
import com.bbd.dao.SellerDao;
import com.bbd.entity.Buyer;
import com.bbd.entity.Seller;
import com.bbd.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private SellerDao sellerDao;

	@Override
	public int regist(Buyer newBuyer, String code) {
		Buyer isExit1 = buyerDao.selectBuyerByPrimary(newBuyer.getId());
		Seller isExit2 = sellerDao.selectSellerByPrimary(newBuyer.getId());
		if (isExit1 != null || isExit2 != null) {
			return 2;
		}
		Buyer buyer = buyerDao.selectBuyerByPrimary(newBuyer.getEmail());
		if (buyer == null) {
			return 0;
		}
		if (buyer.getPwd().equals(code)) {
			if (newBuyer.getUserPic() == null) newBuyer.setUserPic("F://save/user_pic/dafult.jpg");
			buyerDao.insertBuyer(newBuyer);
			buyerDao.deleteForCode(buyer.getId());
			return 1;
		}
		return 3;
	}

	@Override
	public String login(String id, String pwd) {
		Buyer buyer = buyerDao.selectBuyerByPrimary(id);
		if (buyer == null) {
			return "0";
		}
		if (buyer.getPwd().equals(pwd)) {
			JSONStringer stringer = new JSONStringer();
			String json = stringer.object()
					.key("id").value(buyer.getId())
					.key("email").value(buyer.getEmail())
					.key("birthday").value(buyer.getBirthday())
					.key("sex").value(buyer.getSex())
					.key("name").value(buyer.getName())
					.endObject().toString();
			return json;
		}
		return "0";
	}

}
