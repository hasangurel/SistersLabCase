package com.example.sisterslabapi.dto.response.movie;

import lombok.Builder;

import java.util.Date;

@Builder
public record CreateMovieResponse(
        Long id,

        String name,

        String description,

        Date releaseDate


) {
}
