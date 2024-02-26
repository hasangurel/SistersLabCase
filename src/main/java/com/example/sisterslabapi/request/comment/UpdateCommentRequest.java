package com.example.sisterslabapi.request.comment;

public record UpdateCommentRequest(
        Long id,
        String comment
) {
}
