package com.example.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products extends BaseModel {


    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne //(cascade = CascadeType.PERSIST)
    private Category category;

}
