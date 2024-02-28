package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.CategoryConverter;
import com.example.sisterslabapi.dto.converter.MovieConverter;
import com.example.sisterslabapi.model.Category;
import com.example.sisterslabapi.repository.CategoryRepository;
import com.example.sisterslabapi.dto.request.category.CreateCategoryRequest;
import com.example.sisterslabapi.dto.request.category.UpdateCategoryRequest;
import com.example.sisterslabapi.dto.response.category.CreateCategoryResponse;
import com.example.sisterslabapi.dto.response.category.GetCategoryResponse;
import com.example.sisterslabapi.dto.response.category.UpdateCategoryResponse;
import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.dto.response.rating.GetRatingResponse;
import com.example.sisterslabapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryConverter converter;
    private final MovieConverter movieConverter;

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
    public CreateCategoryResponse create(CreateCategoryRequest request) {
        return converter.convertEntityToCreateResponse(converter.convertCreateRequestToEntity(request));
    }
    public UpdateCategoryResponse updateMovies(UpdateCategoryRequest request){
        findById(request.id()).setMovies(movieConverter.getAllById(request.movieIds()));
        return converter.convertEntityToUpdateResponse(findById(request.id()));

    }
    public UpdateCategoryResponse updateNameById(UpdateCategoryRequest request){
        findById(request.id()).setName(request.name());
        return converter.convertEntityToUpdateResponse(findById(request.id()));
    }
    public List<GetCategoryResponse> getAll(){
        return converter.convertEntityToGetAllResponse(repository.findAll());
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
