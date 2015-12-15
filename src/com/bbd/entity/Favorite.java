package com.bbd.entity;

public class Favorite {
    private String id;

    private String treasureId;

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
}