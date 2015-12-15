package com.bbd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.dao.UserDao;
import com.bbd.entity.User;
import com.bbd.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public String getPassword(String userId) {
		User user = userDao.selectByP(userId);
		return user == null ? "null" : user.getPassword();
	}

}
