package com.example.sisterslabapi.dto.request.movie;

import com.example.sisterslabapi.model.Rating;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public record UpdateMovieRequest(
        @NotBlank Long id,
        String name,
        String description,
        Date releaseDate

) {
}
