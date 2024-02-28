package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.RatingConverter;
import com.example.sisterslabapi.dto.request.rating.CreateRatingRequest;
import com.example.sisterslabapi.dto.request.rating.UpdateRatingRequest;
import com.example.sisterslabapi.dto.response.rating.CreateRatingResponse;
import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import com.example.sisterslabapi.dto.response.rating.UpdateRatingResponse;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;

    private final RatingConverter converter;

    public CreateRatingResponse create(CreateRatingRequest request) {
        return converter.convertEntityToCreateRatingResponse(converter.convertCreateRatingRequestToEntity(request));
    }

    public GetRatingResponse getRating(Long id) {
        return converter.convertEntityToGetRatingResponse(findById(id));
    }

    public List<GetRatingResponse> getRatingByMovieId(Long movieId) {
        Movie movie = repository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found.")).getMovie();
        return converter.convertEntityToGetAllRatingResponse(movie.getRatings());
    }

    public UpdateRatingResponse update(UpdateRatingRequest request) {
        return converter.convertEntityToUpdateRatingResponse(converter.convertUpdateRatingRequestToEntity(request));
    }

    private Rating findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
