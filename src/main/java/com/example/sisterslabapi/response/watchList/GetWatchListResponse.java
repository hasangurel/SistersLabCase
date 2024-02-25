package com.example.sisterslabapi.response.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;

import java.util.Date;
import java.util.List;

public record GetWatchListResponse(
        Long id,

        User user,

        List<Movie> movie,
        Date dateAdded,

        Boolean watched
) {
}
