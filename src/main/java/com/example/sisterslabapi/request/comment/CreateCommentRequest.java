package com.example.sisterslabapi.request.comment;

import lombok.Builder;

public record CreateCommentRequest(
         Long userId,
         Long movieId,
         String comment
) {
}
