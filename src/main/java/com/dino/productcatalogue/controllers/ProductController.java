package com.dino.productcatalogue.controllers;

import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.services.FakeStoreProductService;
import com.dino.productcatalogue.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public String welcomePage(){
        return "Welcome to Springboot";
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws IllegalAccessException {
        // call service layer with this id
        if(id<=0){
            throw new IllegalAccessException("id not found");
        }
        Product product = productService.getProductById(id);
        if(product == null) {
            throw new IllegalAccessException("product not found");
        }
        ProductDto productDto = convertToProductDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
    @GetMapping()
    public List<ProductDto> getAllProduct(){
        List<ProductDto> products = new ArrayList<>();
        List<Product> productList = productService.getAllProduct();
        for(Product product : productList){
            products.add(convertToProductDto(product));
        }
        return products;
    }
    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        ResponseEntity<Product> product = productService.replaceProduct(productDto,id);
        if(product.getStatusCode().is2xxSuccessful() && product.getBody()!=null){
            return convertToProductDto(product.getBody());
        }
        return null;
    }

    private ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        //productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
