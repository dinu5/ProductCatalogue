package com.dino.productcatalogue.dtos;

import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private CategoryDto category;
    private Double price;
}
