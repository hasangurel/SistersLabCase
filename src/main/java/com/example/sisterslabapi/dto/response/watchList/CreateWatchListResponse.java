package com.example.sisterslabapi.dto.response.watchList;

import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.dto.response.user.GetUserResponse;
import lombok.Builder;

import java.util.Date;

@Builder
public record CreateWatchListResponse(
        Long id,

        GetUserResponse user,

        GetMovieResponse movie,
        Date dateAdded,

        Boolean watched
) {
}
