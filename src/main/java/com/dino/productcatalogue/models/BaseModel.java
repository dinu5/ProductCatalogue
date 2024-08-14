package com.dino.productcatalogue.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    public Long id;
    public  Date createdAt;
    public Date lastUpdatedAt;


}
