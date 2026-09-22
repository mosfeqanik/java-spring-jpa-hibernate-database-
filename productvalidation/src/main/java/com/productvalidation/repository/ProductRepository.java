package com.productvalidation.repository;

import com.productvalidation.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return products;
    }
}