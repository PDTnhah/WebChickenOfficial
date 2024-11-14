package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Rating;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class RatingResponse {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;


    @JsonProperty("product_id")
    private Long productId;


    private Integer stars;

    public static RatingResponse fromRating(Rating rating) {
        return RatingResponse.builder().id(rating.getId())
                .userId(rating.getUser().getId())
                .productId(rating.getProduct().getId())
                .stars(rating.getStars())
                .build();
    }
}
