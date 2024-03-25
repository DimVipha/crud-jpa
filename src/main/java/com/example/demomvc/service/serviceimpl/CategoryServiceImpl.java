package com.example.demomvc.service.serviceimpl;

import com.example.demomvc.dto.CategoryRequest;
import com.example.demomvc.dto.CategoryResponse;
import com.example.demomvc.model.Category;
import com.example.demomvc.model.Product;
import com.example.demomvc.repository.CategoryRepository;
import com.example.demomvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> findCategories() {
         List<Category> categories=categoryRepository.findAll();

        return categories.stream().map(category -> new CategoryResponse(
                category.getName(),
                category.getDescription()
        )).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Category has not been found!!"
                )
        );
        return new CategoryResponse(category.getName(),category.getDescription());
    }

    @Override
    public void createCategories( CategoryRequest request) {
        if(categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"Category Has already existed !!!"
            );
        }

        Category category=new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);

    }

    @Override
    public CategoryResponse editeCategoryById(Integer id, CategoryRequest request) {
        if (categoryRepository.existsById(id) && categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already exists"
            );
        }
        Category category=categoryRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Category has not been found"
                )
        );
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
        return this.findCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id) ){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"Category has not been found"
            );
        }
        categoryRepository.deleteById(id);
    }
}
