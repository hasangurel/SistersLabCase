package com.example.sisterslabapi.dto.response.comment;

import lombok.Builder;

@Builder
public record GetCommentResponse(
        String comment
) {
}
