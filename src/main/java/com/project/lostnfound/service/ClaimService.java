package com.project.lostnfound.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.lostnfound.dto.ClaimResponse;
import com.project.lostnfound.dto.CreateClaimRequest;
import com.project.lostnfound.exception.BadRequestException;
import com.project.lostnfound.exception.ResourceNotFoundException;
import com.project.lostnfound.model.Claim;
import com.project.lostnfound.model.ClaimStatus;
import com.project.lostnfound.model.Item;
import com.project.lostnfound.model.ItemStatus;
import com.project.lostnfound.model.User;
import com.project.lostnfound.repository.ClaimRepository;
import com.project.lostnfound.repository.ItemRepository;
import com.project.lostnfound.repository.UserRepository;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ClaimService(ClaimRepository claimRepository, ItemService itemService, ItemRepository itemRepository,
            UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ClaimResponse createClaim(CreateClaimRequest request) {
        if (request.getItemId() == null) {
            throw new BadRequestException("itemId is required");
        }
        if (request.getUserId() == null) {
            throw new BadRequestException("userId is required");
        }

        Item item = itemService.getItemEntityById(request.getItemId());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        if (item.getStatus() != ItemStatus.FOUND) {
            throw new BadRequestException("Claims can be created only for FOUND items");
        }

        boolean alreadyPending = claimRepository.existsByItemIdAndUserIdAndStatus(item.getId(), user.getId(),
                ClaimStatus.PENDING);
        if (alreadyPending) {
            throw new BadRequestException("You already have a pending claim for this item");
        }

        Claim claim = new Claim();
        claim.setItem(item);
        claim.setUser(user);
        claim.setStatus(ClaimStatus.PENDING);
        claim.setProofDocument(null);

        Claim saved = claimRepository.save(claim);
        return toClaimResponse(saved);
    }

    public List<ClaimResponse> getAllClaims() {
        return claimRepository.findAll().stream().map(this::toClaimResponse).toList();
    }

    public List<ClaimResponse> getClaimsByStatus(ClaimStatus status) {
        return claimRepository.findByStatus(status).stream().map(this::toClaimResponse).toList();
    }

    public List<ClaimResponse> getClaimsByItem(Long itemId) {
        return claimRepository.findByItemId(itemId).stream().map(this::toClaimResponse).toList();
    }

    public ClaimResponse updateClaimStatus(Long claimId, ClaimStatus status) {
        if (status == null) {
            throw new BadRequestException("Status is required");
        }

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + claimId));

        claim.setStatus(status);
        if (status == ClaimStatus.APPROVED) {
            Item item = claim.getItem();
            item.setStatus(ItemStatus.CLAIMED);
            itemRepository.save(item);
        }

        Claim updated = claimRepository.save(claim);
        return toClaimResponse(updated);
    }

    private ClaimResponse toClaimResponse(Claim claim) {
        return new ClaimResponse(
                claim.getId(),
                claim.getItem().getId(),
                claim.getItem().getName(),
                claim.getUser().getId(),
                claim.getUser().getName(),
                claim.getProofDocument(),
                claim.getStatus());
    }
}
