package com.bbd.dao;

import com.bbd.entity.User;

public interface UserDao {
	User selectByP(String userId);
}
