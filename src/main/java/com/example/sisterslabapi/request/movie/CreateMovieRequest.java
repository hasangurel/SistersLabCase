package com.example.sisterslabapi.request.movie;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CreateMovieRequest(
        @NotBlank String name,

        @NotBlank String description,

        @NotBlank Date releaseDate
) {
}
