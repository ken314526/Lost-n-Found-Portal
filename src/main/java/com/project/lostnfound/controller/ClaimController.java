package com.project.lostnfound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lostnfound.dto.ClaimResponse;
import com.project.lostnfound.dto.CreateClaimRequest;
import com.project.lostnfound.dto.UpdateClaimStatusRequest;
import com.project.lostnfound.model.ClaimStatus;
import com.project.lostnfound.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<ClaimResponse> createClaim(@RequestBody CreateClaimRequest request) {
        ClaimResponse response = claimService.createClaim(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClaimResponse>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ClaimResponse>> getClaimsByStatus(@PathVariable ClaimStatus status) {
        return ResponseEntity.ok(claimService.getClaimsByStatus(status));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<ClaimResponse>> getClaimsByItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(claimService.getClaimsByItem(itemId));
    }

    @PutMapping("/{claimId}/status")
    public ResponseEntity<ClaimResponse> updateClaimStatus(@PathVariable Long claimId,
            @RequestBody UpdateClaimStatusRequest request) {
        return ResponseEntity.ok(claimService.updateClaimStatus(claimId, request.getStatus()));
    }
}
