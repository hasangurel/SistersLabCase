package com.example.sisterslabapi.response.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.response.movie.GetMovieResponse;
import com.example.sisterslabapi.response.user.GetUserResponse;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record CreateWatchListResponse(
        Long id,

        GetUserResponse user,

        GetMovieResponse movie,
        Date dateAdded,

        Boolean watched
) {
}
