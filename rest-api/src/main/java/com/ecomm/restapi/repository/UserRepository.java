package com.ecomm.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.restapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
