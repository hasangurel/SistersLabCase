package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.model.WatchList;
import com.example.sisterslabapi.repository.WatchListRepository;
import com.example.sisterslabapi.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.response.watchList.UpdateWatchListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class WatchListService {
    private final WatchListRepository repository;
    private final MovieService movieService;
    private final UserService userService;
    public CreateWatchListResponse createWatchList(CreateWatchListRequest request) {

        User user = userService.findById(request.userId());
        Date date = new Date();
        // create watch list
        WatchList watchList = new WatchList();
        watchList.setDateAdded(date);
        watchList.setUser(user);
        Movie movie = movieService.findById(request.movieId());
        watchList.setMovie(movie);
        watchList.setWatched(false);
        watchList = repository.save(watchList);
        return CreateWatchListResponse.builder()
                .id(watchList.getId())
                .user(watchList.getUser())
                .movie(watchList.getMovie())
                .dateAdded(watchList.getDateAdded())
                .watched(watchList.getWatched())
                .build();
    }
    public void deleteAllByUserId(Long userId) {
        User byId = userService.findById(userId);
        byId.getWatchLists().forEach(watchList -> repository.deleteById(watchList.getId()));
    }
    public UpdateWatchListResponse setAsWatched(Long id) {
        WatchList watchList = findById(id);
        watchList.setWatched(true);
        WatchList save = repository.save(watchList);
        return UpdateWatchListResponse.builder()
                .id(save.getId())
                .user(save.getUser())
                .movie(save.getMovie())
                .dateAdded(save.getDateAdded())
                .watched(save.getWatched())
                .build();
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public WatchList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("WatchList not found"));
    }
}

