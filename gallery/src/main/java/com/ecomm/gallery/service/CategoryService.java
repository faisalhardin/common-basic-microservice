package com.ecomm.gallery.service;

import com.ecomm.gallery.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, Category category);

    Category create(Category category);

    void delete(Long id);
}
