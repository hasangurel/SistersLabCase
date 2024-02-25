package com.example.sisterslabapi.response.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record CreateWatchListResponse(
        Long id,

        User user,

        Movie movie,
        Date dateAdded,

        Boolean watched
) {
}
