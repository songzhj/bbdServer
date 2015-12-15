package com.bbd.service;

import org.springframework.stereotype.Service;


@Service
public interface UserService {
	String getPassword(String userId);
}
