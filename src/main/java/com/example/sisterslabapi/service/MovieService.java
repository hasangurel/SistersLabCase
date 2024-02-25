package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.request.movie.CreateMovieRequest;
import com.example.sisterslabapi.request.movie.UpdateMovieRequest;
import com.example.sisterslabapi.response.movie.CreateMovieResponse;
import com.example.sisterslabapi.response.movie.GetMovieResponse;
import com.example.sisterslabapi.response.movie.UpdateMovieResponse;
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
                    .rating(movie.getRating())
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
                .rating(save.getRating())
                .build();
    }

    public UpdateMovieResponse updateMovie(UpdateMovieRequest request) {
        Movie movie = findById(request.id());
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setRating(request.rating());
        movie.setName(request.name());
        movie.setId(request.id());
        // save movie
        Movie save = repository.save(movie);
        return UpdateMovieResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .releaseDate(save.getReleaseDate())
                .rating(save.getRating())
                .build();
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
                .rating(movie.getRating())
                .build();
    }

    public Movie findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
