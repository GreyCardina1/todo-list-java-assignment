package com.kamalium.todo.controllers;

import com.kamalium.todo.entity.Item;

public class RestResponse {
    private String status;
    private Item item;

    public RestResponse() {
        status = "";
        item = null;
    }
    public RestResponse(String status, Item item) {
        this.status = status;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
