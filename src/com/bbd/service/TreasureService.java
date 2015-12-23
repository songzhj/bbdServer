package com.bbd.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

public interface TreasureService {
	int publishTreasure(String id, ArrayList<String> pics, HttpServletRequest request);
	int removeTreasure(String tid);
	String searchTreasure(String context);
	String getDetails(String id);
	String getTreasures(String id);
	ArrayList<String> getPics(String id, HttpServletRequest request) throws IllegalStateException, IOException;
}