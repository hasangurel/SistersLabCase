package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Category;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.model.Rating;
import com.example.sisterslabapi.repository.CategoryRepository;
import com.example.sisterslabapi.dto.request.category.CreateCategoryRequest;
import com.example.sisterslabapi.dto.request.category.UpdateCategoryRequest;
import com.example.sisterslabapi.dto.response.category.CreateCategoryResponse;
import com.example.sisterslabapi.dto.response.category.GetCategoryResponse;
import com.example.sisterslabapi.dto.response.category.UpdateCategoryResponse;
import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final MovieService movieService;

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
    public CreateCategoryResponse create(CreateCategoryRequest request) {
        Category category= new Category();
        category.setName(request.name());

        repository.save(category);
        return CreateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
    public UpdateCategoryResponse updateMovies(UpdateCategoryRequest request){
        Category category = findById(request.id());
        List<Long> movieIds = request.movieIds();

        List<GetMovieResponse> responses = movieIds.stream()
                .map(movieService::get)
                .collect(Collectors.toList());
        List<Movie> movies = responses.stream()
                .map(response -> new Movie(response.id(), response.name(), response.description(), response.releaseDate(),
                        response.rating().stream().map(rating -> new Rating(rating.rating())).collect(Collectors.toList())))
                .collect(Collectors.toList());
        category.setMovies(movies);
        repository.save(category);

        return UpdateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .movies(responses)
                .build();

    }
    public UpdateCategoryResponse updateNameById(UpdateCategoryRequest request){
        Category category = findById(request.id());
        category.setName(request.name());
        repository.save(category);
        return UpdateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .movies(category.getMovies().stream()
                        .map(movie -> GetMovieResponse.builder()
                                .id(movie.getId())
                                .name(movie.getName())
                                .releaseDate(movie.getReleaseDate())
                                .description(movie.getDescription())
                                .rating(movie.getRatings().stream().map(rating -> GetRatingResponse.builder()
                                        .rating(rating.getRating())
                                        .build()).collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }
    public List<GetCategoryResponse> getAll(){
        return repository.findAll().stream()
                .map(category -> GetCategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .movies(category.getMovies().stream()
                                .map(movie -> GetMovieResponse.builder()
                                        .id(movie.getId())
                                        .name(movie.getName())
                                        .releaseDate(movie.getReleaseDate())
                                        .description(movie.getDescription())
                                        .rating(movie.getRatings().stream().map(rating -> GetRatingResponse.builder()
                                                .rating(rating.getRating())
                                                .build()).collect(Collectors.toList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
    public List<GetMovieResponse> getAllMovieByCategoryName(String name){
    Category category = repository.findByName(name);
    return category.getMovies().stream()
                .map(movie -> GetMovieResponse.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .releaseDate(movie.getReleaseDate())
                        .description(movie.getDescription())
                        .rating(movie.getRatings().stream().map(rating -> GetRatingResponse.builder()
                                .rating(rating.getRating())
                                .build()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
