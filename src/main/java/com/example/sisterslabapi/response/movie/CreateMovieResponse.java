package com.example.sisterslabapi.response.movie;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Date;

@Builder
public record CreateMovieResponse(
        @NotBlank Long id,

        @NotBlank String name,

        @NotBlank String description,

        @NotBlank Date releaseDate


) {
}
