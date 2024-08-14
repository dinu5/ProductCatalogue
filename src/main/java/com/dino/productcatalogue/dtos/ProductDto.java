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
    private Category category;
    private Double price;
}
