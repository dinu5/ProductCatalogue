package com.dino.productcatalogue.services;

import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProduct();

    public ResponseEntity<Product> replaceProduct(Product product, Long id);
}
