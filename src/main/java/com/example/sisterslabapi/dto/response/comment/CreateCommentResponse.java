package com.example.sisterslabapi.dto.response.comment;

import lombok.Builder;

import java.util.Date;

@Builder
public record CreateCommentResponse(
        Long id,
        Long userId,
        Long movieId,
        String comment
) {
}
