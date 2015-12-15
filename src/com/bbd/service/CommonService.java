package com.bbd.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface CommonService {
	int retrievePassword(String id, String email) throws AddressException, MessagingException;
}
