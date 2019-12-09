package com.ecomm.restapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;


        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column (name = "name")
        private String name;

        @Column (name = "role")
        private String role;
}
