package com.example.sisterslabapi.dto.response.movie;

import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record GetMovieResponse(
        Long id,

        String name,

        String description,

        Date releaseDate,

        List<GetRatingResponse> rating) {
}
