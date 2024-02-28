package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.rating.CreateRatingRequest;
import com.example.sisterslabapi.dto.request.rating.UpdateRatingRequest;
import com.example.sisterslabapi.dto.response.rating.CreateRatingResponse;
import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import com.example.sisterslabapi.dto.response.rating.UpdateRatingResponse;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.repository.RatingRepository;
import com.example.sisterslabapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RatingConverter {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public CreateRatingResponse convertEntityToCreateRatingResponse(Rating rating) {
        return CreateRatingResponse.builder()
                .userId(rating.getUser().getId())
                .movieId(rating.getMovie().getId())
                .rating(rating.getRating())
                .build();
    }

    public Rating convertCreateRatingRequestToEntity(CreateRatingRequest request) {
        Rating rating = new Rating();
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (ratingRepository.findByUserIdAndMovieId(request.userId(), request.movieId()) != null) {
            throw new RuntimeException("Rating already exists");
        }
        rating.setUser(user);
        Movie movie = movieRepository.findById(request.movieId()).orElseThrow(() -> new RuntimeException("Movie not found"));
        rating.setMovie(movie);
        rating.setRating(request.rating());
        ratingRepository.save(rating);
        return rating;
    }

    public GetRatingResponse convertEntityToGetRatingResponse(Rating rating) {
        return GetRatingResponse.builder()
                .rating(rating.getRating())
                .build();
    }

    public List<GetRatingResponse> convertEntityToGetAllRatingResponse(List<Rating> ratings) {
        return ratings.stream().map(rating -> GetRatingResponse.builder()
                .rating(rating.getRating())
                .build()).toList();
    }

    public Rating convertUpdateRatingRequestToEntity(UpdateRatingRequest request) {
        Rating rating = ratingRepository.findByUserIdAndMovieId(request.userId(), request.movieId());
        rating.setRating(request.rating());
        ratingRepository.save(rating);
        return rating;
    }

    public UpdateRatingResponse convertEntityToUpdateRatingResponse(Rating rating) {
        return UpdateRatingResponse.builder()
                .rating(rating.getRating())
                .build();
    }
}
