package com.example.sisterslabapi.response.comment;

import lombok.Builder;

@Builder
public record CreateCommentResponse(
        Long userId,
        Long movieId,
        String comment
) {
}
