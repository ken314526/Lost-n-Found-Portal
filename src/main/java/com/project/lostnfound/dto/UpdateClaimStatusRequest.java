package com.project.lostnfound.dto;

import com.project.lostnfound.model.ClaimStatus;

public class UpdateClaimStatusRequest {
    private ClaimStatus status;

    public UpdateClaimStatusRequest() {
    }

    public UpdateClaimStatusRequest(ClaimStatus status) {
        this.status = status;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
}
