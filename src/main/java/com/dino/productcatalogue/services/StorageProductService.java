package com.dino.productcatalogue.services;

import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> replaceProduct(Product product, Long id) {
        Optional<Product> productResult = productRepository.findById(id);
        if(productResult.isPresent()){
            product.setId(id);
            Product product1 = productRepository.save(product);
            return new ResponseEntity(product1, HttpStatus.CREATED);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
