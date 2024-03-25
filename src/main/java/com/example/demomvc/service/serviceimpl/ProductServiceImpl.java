package com.example.demomvc.service.serviceimpl;

import com.example.demomvc.dto.ProductCreateRequest;
import com.example.demomvc.dto.ProductEditRequest;
import com.example.demomvc.dto.ProductResponse;
import com.example.demomvc.model.Product;
import com.example.demomvc.repository.ProductRepository;
import com.example.demomvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service

public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;
    List<Product > products;
    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {

        products=productRepository.findAll();
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())&& product.getStatus().equals(status))
                .map(
                product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )
        ).toList();

    }

    @Override
    public ProductResponse findProductById(Integer id) {

        Product product=productRepository.findById(id).orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Product has not found")
                        );
                return new ProductResponse(product.getName(),product.getUuid(),product.getPrice(), product.getQty());
    }

    @Override
    public void createProduct(ProductCreateRequest request) {
        if (productRepository.existsByName(request.name())){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,"Product has existed "
            );
        }

        Product product =new Product();
        product.setId(product.getId());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setUuid(UUID.randomUUID().toString());
        product.setImportedDate(LocalDate.now());
        product.setStatus(true);
        productRepository.save(product);
    }

    @Override
    public ProductResponse editProductProductById(Integer id, ProductEditRequest request) {
        if (productRepository.existsById(id) && productRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"Product already existed !!!!"
            );
        }


       Product  product=productRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Product has not been found !!!"
                )
        );
        product.setName(request.name());
        product.setPrice(request.price());
        productRepository.save(product);
        return this.findProductById(id);
    }

    @Override
    public void deleteProductById(Integer id) {
        if (!productRepository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Product has not been found");
        }
        productRepository.deleteById(id);
    }
}
