package com.example.sisterslabapi.dto.request.comment;

public record CreateCommentRequest(
        Long userId,
        Long movieId,
        String comment
) {
}
