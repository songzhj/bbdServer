package com.bbd.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

@Service
public interface RegisterCodeService {
	void sendCode(String recipient) throws AddressException, MessagingException;
}
