package com.bbd.service;

public interface TreasureService {
	int publishTreasure(String data);
	int removeTreasure(String tid);
	String searchTreasure(String context);
	String getDetails(String id);
	String getTreasures(String id);
}