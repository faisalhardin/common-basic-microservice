package com.ecomm.product.entity;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name ="creationTime")
    @CreationTimestamp
    private Date creationTime;

}
