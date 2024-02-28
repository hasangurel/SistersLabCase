package com.example.sisterslabapi.dto.request.watchList;

import com.example.sisterslabapi.model.Movie;

import java.util.List;

public record UpdateWatchListRequest(
        List<Movie> movie,
        Boolean watched
) {
}
