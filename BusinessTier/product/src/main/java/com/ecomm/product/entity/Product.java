package com.ecomm.product.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "imageUrl")
    private  String imageUrl;

    @Column(name = "tag")
    private String tag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="creationTime")
    private java.util.Date creationTime;

}
