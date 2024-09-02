package com.example.productservice.Services;

import com.example.productservice.DTOS.categoryRequestDTO;
import com.example.productservice.DTOS.categoryResponseDTO;

import java.util.List;

public interface CategoryServices {

    categoryResponseDTO getCategory(String category);
    List<categoryResponseDTO> getAllCategories();
    categoryResponseDTO addCategory(categoryRequestDTO requestDTO);
    categoryResponseDTO updateCategory(Long id,categoryRequestDTO requestDTO);
    categoryResponseDTO deleteCategory(Long id);
    categoryResponseDTO replaceCategory(Long id,categoryRequestDTO requestDTO);

}
