package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.request.movie.CreateMovieRequest;
import com.example.sisterslabapi.request.movie.UpdateMovieRequest;
import com.example.sisterslabapi.response.movie.CreateMovieResponse;
import com.example.sisterslabapi.response.movie.GetMovieResponse;
import com.example.sisterslabapi.response.movie.UpdateMovieResponse;
import com.example.sisterslabapi.response.rating.GetRatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;

    public List<GetMovieResponse> getAll() {
        List<Movie> all = repository.findAll();
        List<GetMovieResponse> responses = new ArrayList<>();

        for (Movie movie : all) {
            GetMovieResponse response = GetMovieResponse.builder()
                    .id(movie.getId())
                    .name(movie.getName())
                    .description(movie.getDescription())
                    .releaseDate(movie.getReleaseDate())
                    .rating(movie.getRatings())
                    .build();
            responses.add(response);
        }

        return responses;
    }

    public CreateMovieResponse createMovie(CreateMovieRequest request) {
        Movie movie = new Movie();
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setName(request.name());
        Movie save = repository.save(movie);
        return CreateMovieResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .releaseDate(save.getReleaseDate())
                .build();
    }

    public UpdateMovieResponse updateMovie(UpdateMovieRequest request) {
        Movie movie = findById(request.id());
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setRatings(request.rating());
        movie.setName(request.name());
        movie.setId(request.id());
        // save movie
        Movie save = repository.save(movie);
        return UpdateMovieResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .releaseDate(save.getReleaseDate())
                .rating(save.getRatings())
                .build();
    }
    public Double getAverageRating(Long id) {
        Movie movie = findById(id);
        if (movie.getRatings().isEmpty()) throw new RuntimeException("No ratings found for this movie.");
        List<Rating> ratings = movie.getRatings();
        Double totalRating = 0.0;
        Integer ratingCount = 0;
        Double averageRating = 0.0;
        totalRating = ratings.stream().mapToDouble(Rating::getRating).sum();
        ratingCount = ratings.size();

        averageRating = totalRating / ratingCount;

        return averageRating;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public GetMovieResponse get(Long id) {
        Movie movie = findById(id);
        return GetMovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRatings())
                .build();
    }

    public Movie findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }




}
