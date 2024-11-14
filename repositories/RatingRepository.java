package com.project.shopapp.repositories;

import com.project.shopapp.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByProductId(Long productId);

    Optional<Rating> findByProductIdAndUserId(Long productId, Long userId);

}
