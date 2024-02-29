package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.dto.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.GetWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.UpdateWatchListResponse;
import com.example.sisterslabapi.exception.Constant;
import com.example.sisterslabapi.exception.UserAlreadyAddedThisMovieException;
import com.example.sisterslabapi.exception.UserHasNotWatchListError;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.model.WatchList;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.repository.UserRepository;
import com.example.sisterslabapi.repository.WatchListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WatchListConverter {
    private final WatchListRepository repository;
    private final MovieConverter movieConverter;
    private final UserConverter userConverter;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    public CreateWatchListResponse convertEntityToResponse(WatchList watchList) {
        repository.save(watchList);
        return CreateWatchListResponse.builder()
                .id(watchList.getId())
                .watched(false)
                .dateAdded(new Date())
                .movie(movieConverter.convertEntityToGetResponse(watchList.getMovie()))
                .user(userConverter.convertEntityToGetResponse(watchList.getUser()))
                .build();
    }
    public WatchList convertCreateRequestToEntity(CreateWatchListRequest request) {
        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(repository.findByMovieAndUser(movie, user) != null) {
            throw new UserAlreadyAddedThisMovieException(Constant.USER_ALREADY_ADD_THIS_WATCHLIST);
        }
        WatchList watchList = new WatchList();
        watchList.setDateAdded(new Date());
        watchList.setWatched(false);
        watchList.setMovie(movie);
        watchList.setUser(user);
        return watchList;
    }
    public UpdateWatchListResponse convertEntityToUpdateResponse(WatchList watchList) {
        repository.save(watchList);
        return UpdateWatchListResponse.builder()
                .id(watchList.getId())
                .dateAdded(watchList.getDateAdded())
                .watched(watchList.getWatched())
                .movie(movieConverter.convertEntityToGetResponse(watchList.getMovie()))
                .user(userConverter.convertEntityToGetResponse(watchList.getUser()))
                .build();
    }
    public List<GetWatchListResponse> convertEntityToGetAllResponse(List<WatchList> watchLists) {
        return watchLists.stream()
                .map(this::convertEntityToGetResponse)
                .toList();
    }

    private GetWatchListResponse convertEntityToGetResponse(WatchList watchList) {

        return GetWatchListResponse.builder()
                .id(watchList.getId())
                .dateAdded(watchList.getDateAdded())
                .watched(watchList.getWatched())
                .movieId(watchList.getMovie().getId())
                .userId(watchList.getUser().getId())
                .build();
    }
}
