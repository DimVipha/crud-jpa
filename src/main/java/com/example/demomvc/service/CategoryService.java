package com.example.demomvc.service;

import com.example.demomvc.dto.CategoryRequest;
import com.example.demomvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCategories();
    CategoryResponse findCategoryById(Integer id);
    void createCategories(CategoryRequest request);

    CategoryResponse editeCategoryById(Integer id, CategoryRequest request);
    void deleteCategoryById(Integer id);

}
