package com.example.sisterslabapi.dto.request.movie;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UpdateMovieRequest(
        @NotBlank Long id,
        String name,
        String description,
        Date releaseDate

) {
}
