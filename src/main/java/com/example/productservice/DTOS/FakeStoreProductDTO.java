package com.example.productservice.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String Category;
    private Double price;
    private String imageUrl;
}
