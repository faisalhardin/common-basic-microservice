package com.ecomm.restapi.service;

import java.util.List;

import com.ecomm.restapi.entity.User;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User update(Long id, User user);

    User create(User user);

    void delete(Long id);
}
