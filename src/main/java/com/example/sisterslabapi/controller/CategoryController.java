package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.dto.request.category.CreateCategoryRequest;
import com.example.sisterslabapi.dto.request.category.UpdateCategoryRequest;
import com.example.sisterslabapi.dto.response.category.CreateCategoryResponse;
import com.example.sisterslabapi.dto.response.category.GetCategoryResponse;
import com.example.sisterslabapi.dto.response.category.UpdateCategoryResponse;
import com.example.sisterslabapi.dto.response.movie.GetMovieResponse;
import com.example.sisterslabapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<CreateCategoryResponse> createCategory(@RequestBody CreateCategoryRequest request){
        return new ResponseEntity<>(categoryService.create(request), HttpStatus.CREATED);
    }
    @PutMapping("/update/movies")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest request){
        return new ResponseEntity<>(categoryService.updateMovies(request), HttpStatus.OK);
    }
    @PutMapping("/update/name")
    public ResponseEntity<UpdateCategoryResponse> updateCategoryName(@RequestBody UpdateCategoryRequest request){
        return new ResponseEntity<>(categoryService.updateNameById(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<GetCategoryResponse>> getCategory(){
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/getAllMoviesByCategoryName/{name}")
    public ResponseEntity<List<GetMovieResponse> > getCategoryById(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getAllMovieByCategoryName(name), HttpStatus.OK);
    }
}