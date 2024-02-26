package com.example.sisterslabapi.response.rating;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import lombok.Builder;

@Builder
public record GetRatingResponse(
        Double rating
) {
}
