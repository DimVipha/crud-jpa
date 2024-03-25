package com.example.demomvc.controller;

import com.example.demomvc.dto.ProductCreateRequest;
import com.example.demomvc.dto.ProductEditRequest;
import com.example.demomvc.dto.ProductResponse;
import com.example.demomvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private  final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    void createProducts(@Valid @RequestBody ProductCreateRequest request){
        productService.createProduct(request);
  }

    @GetMapping()
    List<ProductResponse> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                       @RequestParam(required = false,defaultValue = "true") Boolean status){
      return  productService.findProducts(name,status);
  }

   @GetMapping("/{id}")
    ProductResponse findProductById(@Valid @PathVariable Integer id){
        return productService.findProductById(id);
  }

  @PutMapping("/{id}")
    void editProductById(@Valid @PathVariable Integer id,
                         @Valid @RequestBody ProductEditRequest request){
        productService.editProductProductById(id,request);
  }

    @DeleteMapping("/{id}")
    void deleteById(@Valid @PathVariable Integer id){
        productService.deleteProductById(id);
  }
}
