package com.ecomm.gallery.service;

import com.ecomm.gallery.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GalleryService {

    List<Gallery> findAll();

    Page<Gallery> findByUserId(Long id, Pageable page);

    Gallery findById(Long id);

    Gallery update(Long id, Gallery gallery);

    Gallery create(Gallery gallery);

    void delete(Long id);

}
