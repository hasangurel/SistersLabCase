package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.movie.CreateMovieRequest;
import com.example.sisterslabapi.dto.request.movie.UpdateMovieRequest;
import com.example.sisterslabapi.dto.response.movie.CreateMovieResponse;
import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.dto.response.movie.UpdateMovieResponse;
import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieConverter {
    private final MovieRepository repository;
    private final RatingConverter ratingConverter;

    public List<GetMovieResponse> convertEntityToGetAllResponse(List<Movie> movies) {
        return movies.stream()
                .map(this::convertEntityToGetResponse)
                .toList();
    }

    public List<Movie> getAllById(List<Long> movieIds) {
        return movieIds.stream()
                .map(this::findById)
                .toList();
    }

    public UpdateMovieResponse convertEntityToUpdateResponse(Movie movie) {
        return UpdateMovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRatings().stream().map(rating -> GetRatingResponse.builder()
                                .rating(rating.getRating())
                                .build())
                        .toList())
                .build();
    }

    public Movie convertUpdateRequestToEntity(UpdateMovieRequest request) {

        Movie movie = findById(request.id());
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setName(request.name());
        return movie;
    }

    public Movie convertCreateRequestToEntity(CreateMovieRequest request) {
        Movie movie = new Movie();
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setName(request.name());
        return movie;
    }

    public CreateMovieResponse convertEntityToResponse(Movie movie) {
        return CreateMovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    public GetMovieResponse convertEntityToGetResponse(Movie movie) {
        return GetMovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRatings().stream().map(rating -> GetRatingResponse.builder()
                                .rating(rating.getRating())
                                .build())
                        .toList())
                .build();

    }

    public Movie findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
    //entity to response
}
