package com.ecomm.gallery.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="gallery")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="url")
    private String url;

    @Column(name="userId")
    private Long userId;

    @Column(name="date")
    private String date;

    @ManyToOne
//    @JoinColumn(name="id")
    private Category category;
}

