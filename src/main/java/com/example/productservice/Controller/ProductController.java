package com.example.productservice.Controller;

import com.example.productservice.DTOS.FakeStoreProductDTO;
import com.example.productservice.Models.Products;
import com.example.productservice.Services.FakestoreProductsSercies;
import com.example.productservice.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductServices productServices;

    @Autowired
    public ProductController(@Qualifier("SelfProductServices") ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<FakeStoreProductDTO>> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDTO>> response = new ResponseEntity<>(productServices.getAllProductsDTO(), HttpStatus.OK);
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getSingleProduct(@PathVariable("id") Long id){
        ResponseEntity<Products> productsResponseEntity = new ResponseEntity<>(productServices.getSingleProduct(id), HttpStatus.OK);
        return productsResponseEntity;
    }
    @PostMapping("/add")
    public ResponseEntity<Products> addProduct(@RequestBody Products products){
        ResponseEntity<Products> response = new ResponseEntity<>(productServices.addProduct(products), HttpStatus.CREATED);

        return response;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") Long id,@RequestBody Products products){
        ResponseEntity<Products> response;
        response = new ResponseEntity<>(productServices.updateProducts(id, products), HttpStatus.OK);
        return response;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Products> replaceProduct(@PathVariable("id") Long id, @RequestBody Products pruoducts){
        ResponseEntity<Products> response = new ResponseEntity<>(productServices.replaceProducts(id, pruoducts), HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        ResponseEntity<String> response = new ResponseEntity<>(productServices.deleteProduct(id), HttpStatus.OK);
        return response;
    }

}
