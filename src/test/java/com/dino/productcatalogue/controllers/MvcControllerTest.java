package com.dino.productcatalogue.controllers;

import com.dino.productcatalogue.dtos.ProductDto;
import com.dino.productcatalogue.models.Product;
import com.dino.productcatalogue.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class MvcControllerTest {

    @MockBean
    IProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_getAllProduct_testIndividualProductContent() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("iPhone13");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("iPhone15");

        List<Product> productList = new ArrayList();
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProduct()).thenReturn(productList);

        // For debugging
        //------------------------------------------------------------

        String json = objectMapper.writeValueAsString(product1);
        System.out.println("****************"+json);

        // ------------------------------------------------------------

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$[0].title").value("iPhone13"))
                .andExpect(jsonPath("$[1].title").value("iPhone15"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void Test_getAllProduct_StatusOnly() throws Exception {
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void Test_getAllProducts_TestContent() throws Exception {
        //Arrange
        Product products = new Product();
        products.setId(1L);
        products.setTitle("iPhone6");
        List<Product> productList = new ArrayList<>();
        productList.add(products);

        when(productService.getAllProduct()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)));

    }
}
