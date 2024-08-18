package com.dino.productcatalogue.controllers;

import com.dino.productcatalogue.dtos.CategoryDto;
import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.services.FakeStoreProductService;
import com.dino.productcatalogue.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        Product product = convertToProduct(productDto);
        ResponseEntity<Product> productResponseEntity = productService.replaceProduct(product,id);
        if(productResponseEntity.getStatusCode().is2xxSuccessful() && productResponseEntity.getBody()!=null){
            return convertToProductDto(productResponseEntity.getBody());
        }
        return null;
    }

    private Product convertToProduct(ProductDto productDto) {
        Product product = new Product();;
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }

    private ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
//        if(product.getCategory()!=null){
//            CategoryDto categoryDto = new CategoryDto();
//            categoryDto.setName(product.getCategory().getName());
//            categoryDto.setDescription(product.getCategory().getDescrption());
//        }
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
