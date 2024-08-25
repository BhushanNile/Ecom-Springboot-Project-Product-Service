package com.example.productservice.Services;

import com.example.productservice.DTOS.FakeStoreProductDTO;
import com.example.productservice.Exceptions.ProductNotFoundException;
import com.example.productservice.Models.Products;

import java.util.List;

public interface ProductServices {

      Products getSingleProduct(Long id) throws ProductNotFoundException;
      List<Products> getAllProducts();
      Products replaceProducts( Long id,Products products);
      Products  updateProducts(Long id,Products products);
      Products addProduct(Products products);
      List<FakeStoreProductDTO> getAllProductsDTO();
      String deleteProduct(Long id);
}
