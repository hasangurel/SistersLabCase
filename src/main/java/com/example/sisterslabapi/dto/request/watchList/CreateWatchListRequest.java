package com.example.sisterslabapi.dto.request.watchList;

import jakarta.validation.constraints.NotNull;

public record CreateWatchListRequest(
        @NotNull Long userId,
        @NotNull Long movieId
) {
}
