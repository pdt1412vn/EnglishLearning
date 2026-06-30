package com.example.englishlearning.service;

import com.example.englishlearning.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Integer id);

    User save(User user);

    User update(Integer id, User user);

    void delete(Integer id);

    User login(String email, String password);

}
