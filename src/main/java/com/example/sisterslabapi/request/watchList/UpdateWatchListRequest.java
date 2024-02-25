package com.example.sisterslabapi.request.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public record UpdateWatchListRequest(
        List<Movie> movie,

        Boolean watched
) {
}
