package com.example.sisterslabapi.request.watchList;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record CreateWatchListRequest(

         @NotNull Long userId,

         @NotNull Long movieId


) {
}
