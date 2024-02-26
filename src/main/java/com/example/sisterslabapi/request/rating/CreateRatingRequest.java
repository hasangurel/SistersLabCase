package com.example.sisterslabapi.request.rating;

import lombok.Builder;

@Builder
public record CreateRatingRequest(
        Long userId,
         Double rating,
         Long movieId
) {
}
