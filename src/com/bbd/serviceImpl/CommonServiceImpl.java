package com.bbd.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.BuyerDao;
import com.bbd.dao.SellerDao;
import com.bbd.entity.Buyer;
import com.bbd.entity.Seller;
import com.bbd.service.CommonService;
import com.bbd.util.PropertyReader;
import com.bbd.util.SimpleMailSender;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private SellerDao sellerDao;
	
	@Override
	public int retrievePassword(String id, String email) throws AddressException, MessagingException {
		Buyer buyer = buyerDao.selectBuyerByPrimary(id);
		Seller seller = sellerDao.selectForCode(id);
		if (buyer == null && seller == null) return 0;
		if (buyer != null) {
			if (buyer.getEmail().equals(email)) {
				String pwd = buyer.getPwd();
				sendEmail(id, pwd, email);
				return 1;
			} else {
				return 2;
			}
		}
		if (seller != null) {
			if (seller.getEmail().equals(email)) {
				String pwd = seller.getPwd();
				sendEmail(id, pwd, email);
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}

	private void sendEmail(String id, String pwd, String email) throws AddressException, MessagingException {
		PropertyReader prop = new PropertyReader("/mail.properties");
		SimpleMailSender mail = new SimpleMailSender(
				prop.getProperties("smtpUrl"), prop.getProperties("username"),
				prop.getProperties("password"));

		mail.send(email, prop.getProperties("title_retrieve"), "找回密码: 账号: "+ id + " 密码: " + pwd);
	}
	
}
