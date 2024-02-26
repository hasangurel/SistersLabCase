package com.example.sisterslabapi.response.comment;

import lombok.Builder;

@Builder
public record GetCommentResponse(
        String comment
) {
}
