package com.ecomm.product.service;

import com.ecomm.product.entity.Product;
import com.ecomm.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Long id, Product product) {
        Product _product = findById(id);
        _product.setName(product.getName());
        _product.setImageUrl(product.getImageUrl());
        _product.setTag(product.getTag());

        return productRepository.save(_product);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByTags(String tags) {
        return productRepository.findByTagContaining(tags);
    }


}
