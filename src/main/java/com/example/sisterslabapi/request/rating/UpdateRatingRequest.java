package com.example.sisterslabapi.request.rating;

import lombok.Builder;

public record UpdateRatingRequest(
        Long userId,
        Double rating,
        Long movieId
) {
}
