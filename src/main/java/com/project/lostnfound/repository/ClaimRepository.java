package com.project.lostnfound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lostnfound.model.Claim;
import com.project.lostnfound.model.ClaimStatus;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(ClaimStatus status);

    List<Claim> findByItemId(Long itemId);

    List<Claim> findByUserId(Long userId);

    boolean existsByItemIdAndUserIdAndStatus(Long itemId, Long userId, ClaimStatus status);
}
