package com.dino.productcatalogue.dtos;

import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private String description;
    //private Category category;
    private Double price;
}
