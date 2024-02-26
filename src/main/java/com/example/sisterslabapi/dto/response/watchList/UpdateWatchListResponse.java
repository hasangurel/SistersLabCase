package com.example.sisterslabapi.dto.response.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record UpdateWatchListResponse(
        Long id,

        User user,

        Movie movie,
        Date dateAdded,

        Boolean watched
) {
}
