package com.example.demomvc.service;

import com.example.demomvc.dto.ProductCreateRequest;
import com.example.demomvc.dto.ProductEditRequest;
import com.example.demomvc.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts(String name, Boolean status);
    ProductResponse findProductById(Integer id);
    void createProduct(ProductCreateRequest request);
    ProductResponse editProductProductById(Integer id, ProductEditRequest request);
    void deleteProductById(Integer id);

}
