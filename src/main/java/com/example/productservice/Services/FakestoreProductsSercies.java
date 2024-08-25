package com.example.productservice.Services;

import com.example.productservice.DTOS.FakeStoreProductDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakestoreProductsSercies")
public class FakestoreProductsSercies implements ProductServices{
    private RestTemplate restTemplate;

    @Autowired
    public FakestoreProductsSercies (RestTemplate resttemplate) {
        this.restTemplate = resttemplate;
    }

    private Products ConvertFakestoreProductDTOToProducts(FakeStoreProductDTO fakeStoreProductDTO) {
        Products products = new Products();
        products.setId(fakeStoreProductDTO.getId());
        products.setTitle(fakeStoreProductDTO.getTitle());
        products.setDescription(fakeStoreProductDTO.getDescription());
        products.setPrice(fakeStoreProductDTO.getPrice());
        products.setImageUrl(fakeStoreProductDTO.getImageUrl());
        products.setCategory(new Category());
        products.getCategory().setName(fakeStoreProductDTO.getCategory());
        return products;
    }
    @Override
    public Products getSingleProduct(Long id) {
       FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.getForObject("https://fakestoreapi.com/products/"+ id, FakeStoreProductDTO.class);

        return ConvertFakestoreProductDTOToProducts(fakeStoreProductDTO);
    }

    @Override
    public List<Products> getAllProducts() {

        FakeStoreProductDTO [] fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class);

        List<Products> products = new ArrayList<Products>();

        for(FakeStoreProductDTO fakeStoreProductDTO1 : fakeStoreProductDTO) {
            products.add(ConvertFakestoreProductDTOToProducts(fakeStoreProductDTO1));
        }

        return products;
    }

    @Override
    public Products replaceProducts(Long id, Products products) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(products.getTitle());
        fakeStoreProductDTO.setPrice(products.getPrice());
        fakeStoreProductDTO.setDescription(products.getDescription());
        fakeStoreProductDTO.setImageUrl(products.getImageUrl());
        fakeStoreProductDTO.setCategory(products.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), Products.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
         FakeStoreProductDTO productDTO =   restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT,requestCallback, responseExtractor);
//            Products products1 =  ConvertFakestoreProductDTOToProducts(productDTO);

        return ConvertFakestoreProductDTOToProducts(fakeStoreProductDTO);
    }

    @Override
    public Products updateProducts(Long id, Products products) {
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), Products.class);
//        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
//                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
//        FakeStoreProductDTO productDTO =   restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PATCH,requestCallback, responseExtractor);
       FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        if(products.getTitle() != null) {
            fakeStoreProductDTO.setTitle(products.getTitle());
        }
        if(products.getDescription() != null) {
            fakeStoreProductDTO.setDescription(products.getDescription());
        }
        if(products.getImageUrl() != null) {
            fakeStoreProductDTO.setImageUrl(products.getImageUrl());
        }
        if(products.getCategory() != null) {
            fakeStoreProductDTO.setCategory(products.getCategory().getName());
        }
        if(products.getPrice() != null) {
            fakeStoreProductDTO.setPrice(products.getPrice());
        }



        return ConvertFakestoreProductDTOToProducts(fakeStoreProductDTO);
    }

    @Override
    public Products addProduct(Products products) {
        FakeStoreProductDTO productDTO = new FakeStoreProductDTO();
        productDTO.setId(products.getId());
        productDTO.setTitle(products.getTitle());
        productDTO.setDescription(products.getDescription());
        productDTO.setPrice(products.getPrice());
        productDTO.setImageUrl(products.getImageUrl());
        productDTO.setCategory(products.getCategory().getName());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), Products.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO =   restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.POST,requestCallback, responseExtractor);
        return ConvertFakestoreProductDTOToProducts(productDTO);
    }

    @Override
    public List<FakeStoreProductDTO> getAllProductsDTO() {
        return List.of();
    }

    @Override
    public String deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/", +id,Products.class);
        return "Successfully deleted";
    }
}
