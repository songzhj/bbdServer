package com.bbd.serviceImpl;

import java.sql.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.BuyerDao;
import com.bbd.entity.Buyer;
import com.bbd.service.RegisterCodeService;
import com.bbd.util.PropertyReader;
import com.bbd.util.SimpleMailSender;

@Service
public class RegisterCodeServiceImpl implements RegisterCodeService {

	@Autowired
	BuyerDao buyerDao;
	
	@Override
	public void sendCode(String recipient) throws AddressException, MessagingException {
		PropertyReader prop = new PropertyReader("/mail.properties");
		SimpleMailSender mail = new SimpleMailSender(
				prop.getProperties("smtpUrl"), prop.getProperties("username"),
				prop.getProperties("password"));
		
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 6; ++i) {
			code += random.nextInt(10);
		}
		mail.send(recipient, prop.getProperties("title_register"), prop.getProperties("content") + ": " +code);
		
		Buyer buyer = new Buyer();
		buyer.setId(recipient);
		buyer.setPwd(code);
		
		if (buyerDao.selectBuyerByPrimary(recipient) == null) {
			buyerDao.insertForCode(buyer);
		} else {
			buyerDao.updateForCode(buyer);
		}
	}

}
