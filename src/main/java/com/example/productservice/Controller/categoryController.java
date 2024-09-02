package com.example.productservice.Controller;

import com.example.productservice.DTOS.categoryResponseDTO;
import com.example.productservice.Services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class categoryController {
    @Autowired
    private CategoryServices categoryServices;

    public categoryController( CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping("/{name}")
    public categoryResponseDTO getCategory( @PathVariable("name") String category){
        return categoryServices.getCategory(category);
    }
}
