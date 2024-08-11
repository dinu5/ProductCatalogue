package com.dino.productcatalogue.controllers;

import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.services.FakeStoreProductService;
import com.dino.productcatalogue.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public String welcomePage(){
        return "Welcome to Springboot";
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws IllegalAccessException {
        // call service layer with this id
        if(id<=0){
            throw new IllegalAccessException("id not found");
        }
        Product product = productService.getProductById(id);
        if(product == null) {}
        ProductDto productDto = convertToProductDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    private ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        //productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
