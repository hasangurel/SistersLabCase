package com.example.sisterslabapi.response.rating;

import lombok.Builder;

@Builder
public record UpdateRatingResponse(
        Double rating

) {
}
