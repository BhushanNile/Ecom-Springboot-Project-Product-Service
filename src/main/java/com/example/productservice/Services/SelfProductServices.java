package com.example.productservice.Services;

import com.example.productservice.DTOS.FakeStoreProductDTO;
import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Products;
import com.example.productservice.Repositorys.CategoryRepository;
import com.example.productservice.Repositorys.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("SelfProductServices")
public class SelfProductServices implements ProductServices{
    private ProductsRepository productsRepository;
    private CategoryRepository categoryRepository;

    public SelfProductServices(
            ProductsRepository productsRepository, CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }
    public FakeStoreProductDTO ConverFakeToProductDTO(Products product) {
        FakeStoreProductDTO fakeStoreProductDTO1 = new FakeStoreProductDTO();
        fakeStoreProductDTO1.setId(product.getId());
        fakeStoreProductDTO1.setTitle(product.getTitle());
        fakeStoreProductDTO1.setDescription(product.getDescription());
        fakeStoreProductDTO1.setCategory(product.getCategory().getName());
        fakeStoreProductDTO1.setPrice(product.getPrice());
        fakeStoreProductDTO1.setImageUrl(product.getImageUrl());
        return fakeStoreProductDTO1;
    }

    @Override
    public Products getSingleProduct(Long id) throws ProductNotFoundException {
    Optional<Products> products = productsRepository.findById(id);
    if (products.isEmpty()){
        throw new ProductNotFoundException("Product with this ID:-" + id + "doesn't exist");
    }
        Products product = products.get();

        return product;
    }

    @Override
    public List<Products> getAllProducts() {
        return List.of();
    }
    public List<FakeStoreProductDTO> getAllProductsDTO() {
        List<Products> products = productsRepository.findAll();
        List<FakeStoreProductDTO> productsDTO = new ArrayList<>();
        for(Products products1 : products){
            productsDTO.add(ConverFakeToProductDTO(products1));
        }
        return productsDTO;
   }

    @Override
    public Products replaceProducts(Long id, Products products) {
        Optional<Products> products1 = productsRepository.findById(id);
        Optional<Category> category = categoryRepository.findByName(products.getCategory().getName());
        if(category.isEmpty()){
            throw new ProductNotFoundException("Product with this ID:-" + id + "doesn't exist");
        }
        Category updateCategory = category.get();
        if (products1.isEmpty()){
            throw new ProductNotFoundException("Product with this ID:-" + id + "doesn't exist");
        }
        Products updatedProduct = products1.get();
        updatedProduct.setTitle(products.getTitle());
        updatedProduct.setDescription(products.getDescription());
        updatedProduct.setCategory(updateCategory);


        updatedProduct.setPrice(products.getPrice());
        updatedProduct.setImageUrl(products.getImageUrl());
        productsRepository.save(updatedProduct);

        return updatedProduct;
    }

    @Override
    public Products updateProducts(Long id, Products products) {
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isEmpty()){
            throw new ProductNotFoundException("Product with this ID:-" + id + "doesn't exist");
        }
         Products currentProduct = productsOptional.get();
         if(products.getCategory() != null){
             currentProduct.setCategory(products.getCategory());
         }
         if(products.getDescription() != null){
             currentProduct.setDescription(products.getDescription());
         }
         if(products.getPrice() != null){
             currentProduct.setPrice(products.getPrice());
         }
         if(products.getTitle() != null){
             currentProduct.setTitle(products.getTitle());
         }

        return currentProduct;
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        if (optionalProducts.isEmpty()){
            throw new ProductNotFoundException("Product with this ID:-" + id + "doesn't exist");
        }

        productsRepository.deleteById(optionalProducts.get().getId());
        return "Products of this ID:-" + id +" Deleted ";
    }

    @Override
    public Products addProduct(Products products) {

        Optional<Category> categoryOptional = categoryRepository.findByName(products.getCategory().getName());
        if(categoryOptional.isEmpty()){
            products.setCategory(categoryRepository.save(products.getCategory()));
        }else{
            products.setCategory(categoryOptional.get());
        }
        return productsRepository.save(products);
    }
}
