package com.example.sisterslabapi.request.comment;

public record UpdateCommentRequest(
        Long userId,
        Long movieId,
        String comment
) {
}
