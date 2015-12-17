package com.bbd.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;

    private String buyerId;

    private String sellerId;

    private String tId;

    private BigDecimal tNum;

    private Double price;

    private Date makeTime;

    private String state;
    
    private String addrId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public BigDecimal gettNum() {
        return tNum;
    }

    public void settNum(BigDecimal tNum) {
        this.tNum = tNum;
    }

    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    
    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId == null ? null : addrId.trim();
    }
}