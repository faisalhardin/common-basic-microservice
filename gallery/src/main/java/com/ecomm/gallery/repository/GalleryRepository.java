package com.ecomm.gallery.repository;

import com.ecomm.gallery.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {


    @Query("select g from Gallery g left join g.category cat where cat.id = :id")
    Gallery findById_JoinCategory(@Param("id") Long id);

}
