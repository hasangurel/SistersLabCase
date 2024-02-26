package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.model.WatchList;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.repository.UserRepository;
import com.example.sisterslabapi.repository.WatchListRepository;
import com.example.sisterslabapi.dto.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.dto.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.GetWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.UpdateWatchListResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchListService {
    private final WatchListRepository repository;
    private final MovieService movieService;
    private final UserService userService;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
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
                .dateAdded(watchList.getDateAdded())
                .watched(watchList.getWatched())
                .build();
    }
    @Transactional
    public void deleteAllByUserId(Long userId) {
        User user= userService.findById(userId);

        repository.deleteAllByUser(user);
    }
    public UpdateWatchListResponse setAsWatched(Long id) {
        WatchList watchList = findById(id);
        watchList.setWatched(true);
        WatchList save = repository.save(watchList);
        return UpdateWatchListResponse.builder()
                .id(save.getId())
                .dateAdded(save.getDateAdded())
                .watched(save.getWatched())
                .build();
    }
    public UpdateWatchListResponse setAsNotWatched(Long id) {
        WatchList watchList = findById(id);
        watchList.setWatched(false);
        WatchList save = repository.save(watchList);
        return UpdateWatchListResponse.builder()
                .id(save.getId())
                .dateAdded(save.getDateAdded())
                .watched(save.getWatched())
                .build();
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<GetWatchListResponse> getByUserID(Long id) {
        User user = userService.findById(id);
        List<WatchList> watchLists = user.getWatchLists();
        List<GetWatchListResponse> responseList = new ArrayList<>();

        for (WatchList watchList : watchLists) {
            GetWatchListResponse response = GetWatchListResponse.builder()
                    .id(watchList.getId())
                    .userId(user.getId())
                    .movieId(watchList.getMovie().getId())
                    .dateAdded(watchList.getDateAdded())
                    .watched(watchList.getWatched())
                    .build();
            responseList.add(response);
        }
        return responseList;
    }
    public WatchList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("WatchList not found"));
    }
}

