package com.dino.productcatalogue.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    public Long id;
    public  Date createdAt;
    public Date lastUpdatedAt;


}
