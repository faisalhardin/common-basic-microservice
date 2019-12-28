package com.ecomm.restapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

        public void setPassword(String password) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(password);
        }
}
