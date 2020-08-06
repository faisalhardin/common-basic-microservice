package com.ecomm.product.service;

import com.ecomm.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product update(Long id, Product product);

    Product create(Product product);

    void delete(Long id);

    List<Product> findByTags(String tags);
}
