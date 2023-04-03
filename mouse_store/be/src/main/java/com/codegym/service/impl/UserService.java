package com.codegym.service.impl;

import com.codegym.dto.UpdateUserForm;
import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.repository.IUserRepository;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;


    @Override
    public Optional<User> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }


    @Override
    public void updateUser(UpdateUserForm updateUserForm) {
        iUserRepository.updateUser(updateUserForm.getName(),
                updateUserForm.getPhoneNumber(),
                updateUserForm.getEmail(),
                updateUserForm.getAddress(),
                updateUserForm.getGender(),
                updateUserForm.getDateOfBirth(),
                updateUserForm.getAvatar(),
                updateUserForm.getUsername());
    }


    @Override
    public void changePassword(String password, String username) {
        iUserRepository.changePassword(password, username);
    }


    @Override
    public void save(User user) {
        iUserRepository.save(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getAvatar());
        User user1 = iUserRepository.findByUsername(user.getUsername()).orElse(null);
        for (Role x : user.getRoles()) {
            assert user1 != null;
            iUserRepository.insertRole(user1.getId(), x.getId());
        }
    }


    @Override
    public Boolean existsByUsername(String username) {
        for (int i = 0; i < iUserRepository.getAllUser().size(); i++) {
            if (iUserRepository.getAllUser().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean existsByEmail(String email) {
        for (int i = 0; i < iUserRepository.getAllUser().size(); i++) {
            if (iUserRepository.getAllUser().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<User> findAll() {
        return iUserRepository.getAllUser();
    }


    @Autowired
    private IUserRepository userRepository;


    @Override
    public Page<User> findAll(String genderSearch, String ageSearch, Pageable pageable) {
        if (genderSearch.equals("Nam")) {
            return userRepository.findAllByGender(true, ageSearch, pageable);
        }
        if (genderSearch.equals("Nữ")) {
            return userRepository.findAllByGender(false, ageSearch, pageable);
        }
        return userRepository.findAll(genderSearch, ageSearch, pageable);
    }

    @Override
    public User userLogin(String username) {
        return iUserRepository.userLogin(username);
    }

    @Override
    public User getUserById(Integer user) {
        return userRepository.findById(user).orElse(null);
    }

    @Override
    public User userLogin1(Integer id) {
        return iUserRepository.findById(id).orElse(null);
    }

    @Override
    public User findById(Integer user) {
        return iUserRepository.findById(user).orElse(null);
    }
}


