package com.example.sisterslabapi.dto.response.rating;

import lombok.Builder;

@Builder
public record UpdateRatingResponse(
        Double rating

) {
}
