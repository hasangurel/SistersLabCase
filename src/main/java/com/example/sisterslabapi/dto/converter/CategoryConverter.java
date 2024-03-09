package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.category.CreateCategoryRequest;
import com.example.sisterslabapi.dto.request.category.UpdateCategoryRequest;
import com.example.sisterslabapi.dto.response.category.CreateCategoryResponse;
import com.example.sisterslabapi.dto.response.category.GetCategoryResponse;
import com.example.sisterslabapi.dto.response.category.UpdateCategoryResponse;
import com.example.sisterslabapi.exception.CategoryAlreadyExistException;
import com.example.sisterslabapi.exception.CategoryIdNotFoundException;
import com.example.sisterslabapi.exception.CategoryNotFoundException;
import com.example.sisterslabapi.exception.Constant;
import com.example.sisterslabapi.model.Category;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.repository.CategoryRepository;
import com.example.sisterslabapi.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryRepository repository;
    private final MovieConverter movieConverter;
    private final MovieRepository movieRepository;

    @Transactional
    public Category convertCreateRequestToEntity(CreateCategoryRequest request) {
        if (repository.findByName(request.name()) != null) {
            throw new CategoryAlreadyExistException("Category already exists");
        }
        Category category = new Category();
        category.setName(request.name());
        return category;
    }

    @Transactional
    public CreateCategoryResponse convertEntityToCreateResponse(Category category) {
        repository.save(category);
        return CreateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Transactional
    public UpdateCategoryResponse convertEntityToUpdateResponse(Category category) {
        if (category.getId() == null) {
            throw new CategoryNotFoundException(Constant.CATEGORY_NOT_FOUND);
        } else {
            repository.save(category);
        }
        return UpdateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .movies(movieConverter.convertEntityToGetAllResponse(category.getMovies()))
                .build();
    }

    public GetCategoryResponse convertEntityToGetResponse(Category category) {
        return GetCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .movies(movieConverter.convertEntityToGetAllResponse(category.getMovies()))
                .build();
    }

    public List<GetCategoryResponse> convertEntityToGetAllResponse(List<Category> categories) {
        return categories.stream()
                .map(this::convertEntityToGetResponse)
                .toList();
    }

    @Transactional
    public Category convertUpdateToEntity(UpdateCategoryRequest request) {
        Category category = findById(request.id());
        if (request.name() != null) {
            category.setName(request.name());
        }
        Movie movie = movieRepository.findById(request.movieId()).orElseThrow(() -> new RuntimeException("Movie not found."));
        category.getMovies().add(movie);
        return category;
    }

    @Transactional
    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryIdNotFoundException(Constant.CATEGORY_ID_NOT_FOUND));
    }

}
