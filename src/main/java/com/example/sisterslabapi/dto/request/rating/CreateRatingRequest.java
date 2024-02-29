package com.example.sisterslabapi.dto.request.rating;

public record CreateRatingRequest(
        Long userId,
        Double rating,
        Long movieId
) {
}
