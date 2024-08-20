package com.dino.productcatalogue.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Double price;
}
