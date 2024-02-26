package com.example.sisterslabapi.dto.response.comment;

import lombok.Builder;

@Builder
public record UpdateCommentResponse(
        Long id,
        Long userId,
        Long movieId,
        String comment
) {
}
