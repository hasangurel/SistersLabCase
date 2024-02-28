package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.MovieConverter;
import com.example.sisterslabapi.dto.request.movie.CreateMovieRequest;
import com.example.sisterslabapi.dto.request.movie.UpdateMovieRequest;
import com.example.sisterslabapi.dto.response.movie.CreateMovieResponse;
import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.dto.response.movie.UpdateMovieResponse;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.repository.CategoryRepository;
import com.example.sisterslabapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;
    private final MovieConverter converter;
    private final CategoryRepository categoryRepository;

    public List<GetMovieResponse> getAll() {
        return converter.convertEntityToGetAllResponse(repository.findAll());
    }

    public CreateMovieResponse createMovie(CreateMovieRequest request) {
        return converter.convertEntityToResponse(repository.save(converter.convertCreateRequestToEntity(request)));
    }

    public UpdateMovieResponse updateMovie(UpdateMovieRequest request) {
        return converter.convertEntityToUpdateResponse(repository.save(converter.convertUpdateRequestToEntity(request)));
    }

    public List<GetMovieResponse> getAllMovieByCategoryName(String name) {
        return converter.convertEntityToGetAllResponse(categoryRepository.findByName(name).getMovies());
    }

    public Double getAverageRating(Long id) {
        return findById(id).getRatings().stream().mapToDouble(Rating::getRating).average().orElseThrow(() -> new RuntimeException("No ratings found for this movie."));
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public GetMovieResponse get(Long id) {
        return converter.convertEntityToGetResponse(findById(id));
    }

    public Movie findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found."));
    }
}
