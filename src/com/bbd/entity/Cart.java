package com.bbd.entity;

import java.math.BigDecimal;

public class Cart {
    private String id;

    private String treasureId;

    private BigDecimal treasureNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(String treasureId) {
        this.treasureId = treasureId == null ? null : treasureId.trim();
    }

    public BigDecimal getTreasureNum() {
        return treasureNum;
    }

    public void setTreasureNum(BigDecimal treasureNum) {
        this.treasureNum = treasureNum;
    }
}