package com.example.demomvc.controller;

import com.example.demomvc.dto.CategoryRequest;
import com.example.demomvc.dto.CategoryResponse;
import com.example.demomvc.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    List<CategoryResponse> findCategories(){
        return categoryService.findCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    CategoryResponse findCategoryById(@PathVariable Integer id){
        return categoryService.findCategoryById(id);
    }

    @PostMapping()
    void createCategories(@Valid @RequestBody CategoryRequest request){
        categoryService.createCategories(request);
    }

    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id,
                                      @Valid @RequestBody CategoryRequest request){
       return categoryService.editeCategoryById(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteCategoryById(@Valid @PathVariable  Integer id){
        categoryService.deleteCategoryById(id);
    }

}
