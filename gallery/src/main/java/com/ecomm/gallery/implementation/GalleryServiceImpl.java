package com.ecomm.gallery.implementation;

import com.ecomm.gallery.entity.Gallery;
import com.ecomm.gallery.repository.GalleryRepository;
import com.ecomm.gallery.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    @Override
    public List<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Page<Gallery> findByUserId(Long userId, Pageable pageable) {
        return galleryRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Gallery findById(Long id) { return galleryRepository.findById(id).get(); }

    @Override
    public Gallery update(Long id, Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery create(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public void delete(Long id) {
        galleryRepository.deleteById(id);
    }


}
