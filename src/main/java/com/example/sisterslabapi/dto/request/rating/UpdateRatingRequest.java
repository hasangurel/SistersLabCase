package com.example.sisterslabapi.dto.request.rating;

public record UpdateRatingRequest(
        Long userId,
        Double rating,
        Long movieId
) {
}
