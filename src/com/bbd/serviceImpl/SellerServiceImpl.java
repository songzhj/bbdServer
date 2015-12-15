package com.bbd.serviceImpl;

import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.BuyerDao;
import com.bbd.dao.SellerDao;
import com.bbd.entity.Buyer;
import com.bbd.entity.Seller;
import com.bbd.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private BuyerDao buyerDao;
	
	@Override
	public int regist(Seller newSeller, String code) {
		Buyer isExit1 = buyerDao.selectBuyerByPrimary(newSeller.getId());
		Seller isExit2 = sellerDao.selectSellerByPrimary(newSeller.getId());
		if (isExit1 != null || isExit2 != null) {
			return 2;
		}
		Seller seller = sellerDao.selectSellerByPrimary(newSeller.getEmail());
		if (seller == null) {
			return 0;
		}
		if (seller.getPwd().equals(code)) {
			if (newSeller.getUserPic() == null) newSeller.setUserPic("F://save/user_pic/dafult.jpg");
			sellerDao.insertSeller(newSeller);
			sellerDao.deleteForCode(seller.getId());
			return 1;
		}
		return 3;
	}

	@Override
	public String login(String id, String pwd) {
		Seller seller = sellerDao.selectSellerByPrimary(id);
		if (seller == null) {
			return "0";
		}
		if (seller.getState().equals("0")) {
			return "2"; // 状态"2"为未通过审核
		}
		if (seller.getPwd().equals(pwd)) {
			JSONStringer stringer = new JSONStringer();
			String json = stringer.object()
					.key("id").value(seller.getId())
					.key("email").value(seller.getEmail())
					.key("birthday").value(seller.getBirthday())
					.key("sex").value(seller.getSex())
					.key("id_card").value(seller.getIdCard())
					.key("phone").value(seller.getPhone())
					.key("name").value(seller.getName())
					.endObject().toString();
			return json;
		}
		return "0";
	}

}
