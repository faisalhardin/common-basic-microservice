package com.ecomm.gallery.service;

import com.ecomm.gallery.entity.Gallery;

import java.util.List;

public interface GalleryService {

    List<Gallery> findAll();

    Gallery findById(Long id);

    Gallery update(Long id, Gallery gallery);

    Gallery create(Gallery gallery);

    void delete(Long id);
}
