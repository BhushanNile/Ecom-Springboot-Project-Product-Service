package com.example.productservice.Services;

import com.example.productservice.DTOS.categoryRequestDTO;
import com.example.productservice.DTOS.categoryResponseDTO;
import com.example.productservice.Repositorys.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryServices implements CategoryServices {
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    

    @Override
    public categoryResponseDTO getCategory(String category) {
        return null;
    }

    @Override
    public List<categoryResponseDTO> getAllCategories() {
        return List.of();
    }

    @Override
    public categoryResponseDTO addCategory(categoryRequestDTO requestDTO) {
        return null;
    }

    @Override
    public categoryResponseDTO updateCategory(Long id, categoryRequestDTO requestDTO) {
        return null;
    }

    @Override
    public categoryResponseDTO deleteCategory(Long id) {
        return null;
    }

    @Override
    public categoryResponseDTO replaceCategory(Long id, categoryRequestDTO requestDTO) {
        return null;
    }
}
