package com.dino.productcatalogue.services;

import com.dino.productcatalogue.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProduct();
}
