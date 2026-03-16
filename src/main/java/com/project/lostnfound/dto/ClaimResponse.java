package com.project.lostnfound.dto;

import com.project.lostnfound.model.ClaimStatus;

public class ClaimResponse {
    private Long id;
    private Long itemId;
    private String itemName;
    private Long userId;
    private String userName;
    private String proofDocument;
    private ClaimStatus status;

    public ClaimResponse() {
    }

    public ClaimResponse(Long id, Long itemId, String itemName, Long userId, String userName, String proofDocument,
            ClaimStatus status) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.userId = userId;
        this.userName = userName;
        this.proofDocument = proofDocument;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProofDocument() {
        return proofDocument;
    }

    public void setProofDocument(String proofDocument) {
        this.proofDocument = proofDocument;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
}
