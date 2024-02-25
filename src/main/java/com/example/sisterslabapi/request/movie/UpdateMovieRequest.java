package com.example.sisterslabapi.request.movie;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UpdateMovieRequest(
        @NotBlank Long id,

         String name,

         String description,

         Date releaseDate,

         Double rating
) {
}
