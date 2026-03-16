package com.project.lostnfound.dto;

import com.project.lostnfound.model.ItemStatus;

public class UpdateItemStatusRequest {
    private ItemStatus status;

    public UpdateItemStatusRequest() {
    }

    public UpdateItemStatusRequest(ItemStatus status) {
        this.status = status;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
