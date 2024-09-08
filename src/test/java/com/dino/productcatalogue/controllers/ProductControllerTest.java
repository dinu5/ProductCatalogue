package com.dino.productcatalogue.controllers;

import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private  ProductController productController;
    @MockBean
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCapture;

    @Test
    void addProduct() {
    }

    @Test
    void Test_getProductById_HappyCase() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setDescription("iPhone12");
        product.setDescription("iPhone 12");
        when(productService.getProductById(productId)).thenReturn(product);
        assertEquals("iPhone12",product.getDescription());
    }

    @Test
    void Test_getProductById_SadCase_id_zero(){
        Long productId = 0L;
        Exception ex = assertThrows(IllegalAccessException.class,() -> productController.getProductById(productId));
        assertEquals(ex.getMessage(),"id not found");
    }

    @Test
    void Test_getProductById_SadCase_id_lessthanzero(){
        Long productId = -1L;
        Exception ex = assertThrows(IllegalAccessException.class,() -> productController.getProductById(productId));
        assertEquals(ex.getMessage(),"invalid id");
    }

    @Test
    public void Test_getProductById_ServiceCalledByExpectedId() throws IllegalAccessException {
        Long productId = 1L;
        Product product1 = new Product();
        product1.setDescription("iPhone13");

        when(productService.getProductById(productId)).thenReturn(product1);

        productController.getProductById(productId);
        verify(productService).getProductById(idCapture.capture());
        assertEquals(productId,idCapture.getValue());
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void replaceProduct() {
    }
}