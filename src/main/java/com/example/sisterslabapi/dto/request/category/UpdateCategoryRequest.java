package com.example.sisterslabapi.dto.request.category;

import lombok.Builder;

import java.util.List;

@Builder
public record UpdateCategoryRequest(
        Long id,
        String name,
        List<Long> movieIds
) {
}
