package com.dino.productcatalogue.clients;

import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreProductClient {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    public ResponseEntity<FakeStoreProductDto> getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("http://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,id);
        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody()!=null){
            return fakeStoreProductDtoResponseEntity;
        }
        return null;
    }
}
