package com.bbd.service;

public interface FavoriteService {

	int add(String tId, String uId);

	int del(String tId, String uId);

	String getFavorites(String id);

}
