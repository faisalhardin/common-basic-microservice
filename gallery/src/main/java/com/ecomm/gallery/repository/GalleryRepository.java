package com.ecomm.gallery.repository;

import com.ecomm.gallery.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Page<Gallery> findAllByUserId(Long id, Pageable pageable);

}
