package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.repository.RatingRepository;
import com.example.sisterslabapi.request.rating.CreateRatingRequest;
import com.example.sisterslabapi.request.rating.UpdateRatingRequest;
import com.example.sisterslabapi.response.movie.GetMovieResponse;
import com.example.sisterslabapi.response.rating.CreateRatingResponse;
import com.example.sisterslabapi.response.rating.GetRatingResponse;
import com.example.sisterslabapi.response.rating.UpdateRatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;
    private final MovieService movieService;
    private final UserService userService;

    public CreateRatingResponse create(CreateRatingRequest request) {
        Rating rating = new Rating();
        User user = userService.findById(request.userId());
        Movie movie = movieService.findById(request.movieId());
        rating.setUser(user);
        rating.setMovie(movie);
        rating.setRating(request.rating());
        repository.save(rating);
        return  CreateRatingResponse.builder()
                .userId(rating.getUser().getId())
                .movieId(rating.getMovie().getId())
                .rating(rating.getRating())
                .build();
    }
    //bunu bir ratingin rate değerini alabilmek için benim ratingi getirebilmem lazım
    //repeating var
    public GetRatingResponse getRating(Long id) {
        Rating rating = findById(id);
        return GetRatingResponse.builder()
                .rating(rating.getRating())
                .build();
    }
    public UpdateRatingResponse update(UpdateRatingRequest request) {
        Rating rating = repository.findByUserIdAndMovieId(request.userId(), request.movieId());
        rating.setRating(request.rating());
        repository.save(rating);
        return UpdateRatingResponse.builder()
                .rating(rating.getRating())
                .build();
    }
    private Rating findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
