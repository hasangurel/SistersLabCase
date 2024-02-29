package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.category.CreateCategoryRequest;
import com.example.sisterslabapi.dto.response.category.CreateCategoryResponse;
import com.example.sisterslabapi.dto.response.category.GetCategoryResponse;
import com.example.sisterslabapi.dto.response.category.UpdateCategoryResponse;
import com.example.sisterslabapi.exception.CategoryAlreadyExistException;
import com.example.sisterslabapi.model.Category;
import com.example.sisterslabapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryRepository repository;
    private final MovieConverter movieConverter;

    public Category convertCreateRequestToEntity(CreateCategoryRequest request) {
       if (repository.findByName(request.name()) != null) {
           throw new CategoryAlreadyExistException("Category already exists");
       }
        Category category = new Category();
        category.setName(request.name());
        return category;
    }

    public CreateCategoryResponse convertEntityToCreateResponse(Category category) {
        repository.save(category);
        return CreateCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public UpdateCategoryResponse convertEntityToUpdateResponse(Category category) {
        repository.save(category);
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
}
