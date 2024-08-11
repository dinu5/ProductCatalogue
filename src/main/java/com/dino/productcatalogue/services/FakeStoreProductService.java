package com.dino.productcatalogue.services;

import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import com.dino.productcatalogue.models.Product;
import jdk.jfr.RecordingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("http://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,id);
        if(fakeStoreProductDto.getStatusCode().is2xxSuccessful() && fakeStoreProductDto.getBody() != null){
            return convertToProduct(fakeStoreProductDto.getBody());
        }
        return null;
    }

    private Product convertToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        //product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of();
    }
}
