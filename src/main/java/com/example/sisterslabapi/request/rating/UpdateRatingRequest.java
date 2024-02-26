package com.example.sisterslabapi.request.rating;

import lombok.Builder;

@Builder
public record UpdateRatingRequest(
        Long userId,
        Double rating,
        Long movieId
) {
}
