package com.dino.productcatalogue.services;

import com.dino.productcatalogue.clients.FakeStoreProductClient;
import com.dino.productcatalogue.dtos.FakeStoreProductDto;
import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import jdk.jfr.RecordingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

//    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = fakeStoreProductClient.getProductById(id);
        if(fakeStoreProductDto.getStatusCode().is2xxSuccessful() && fakeStoreProductDto.getBody()!=null){
            return convertToProduct(fakeStoreProductDto.getBody());
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("http://fakestoreapi.com/products",FakeStoreProductDto[].class).getBody();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public ResponseEntity<Product> replaceProduct(ProductDto productDto, Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = requestForEntity("http://fakestoreapi.com/products/{id}", productDto,FakeStoreProductDto.class,id);
        if(fakeStoreProductDto.getStatusCode().is2xxSuccessful()){
            return new ResponseEntity<>(convertToProduct(fakeStoreProductDto.getBody()),HttpStatus.OK);
        }
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product convertToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        //product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
