package com.example.sisterslabapi.request.rating;

import lombok.Builder;


public record CreateRatingRequest(
        Long userId,
         Double rating,
         Long movieId
) {
}
