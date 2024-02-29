package com.example.sisterslabapi.dto.request.category;

import lombok.Builder;

@Builder
public record UpdateCategoryRequest(
        Long id,
        String name,
        Long movieId
) {
}
