package com.example.sisterslabapi.dto.response.category;

import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateCategoryResponse(
        Long id,
        String name,
        List<GetMovieResponse> movies
) {
}
