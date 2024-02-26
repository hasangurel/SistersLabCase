package com.example.sisterslabapi.response.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record GetWatchListResponse(
        Long id,

        Long userId,

        Long movieId,
        Date dateAdded,

        Boolean watched
) {
}
