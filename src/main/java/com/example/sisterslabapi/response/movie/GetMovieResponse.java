package com.example.sisterslabapi.response.movie;

import com.example.sisterslabapi.model.Rating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record GetMovieResponse(
         @NotBlank Long id,

         @NotBlank String name,

         @NotBlank String description,

         @NotBlank Date releaseDate,

         List<Rating> rating) {
}
