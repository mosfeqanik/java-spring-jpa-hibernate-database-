package com.productvalidation.controller;

import com.productvalidation.model.Product;
import com.productvalidation.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    // CREATE (Validation applied)
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {

        Product savedProduct = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // READ ALL
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(service.getAllProducts());
    }
}