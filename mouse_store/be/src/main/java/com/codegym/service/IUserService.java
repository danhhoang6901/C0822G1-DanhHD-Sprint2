package com.codegym.service;

import com.codegym.dto.UpdateUserForm;
import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findByUsername(String username);


    void updateUser(UpdateUserForm updateUserForm);

    void changePassword(String password, String username);

    void save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    List<User> findAll();

    Page<User> findAll(String genderSearch, String ageSearch, Pageable pageable);

    User userLogin(String username);

    User getUserById(Integer user);

    User userLogin1(Integer id);

    User findById(Integer user);
}
