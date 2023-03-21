package com.codegym.service.impl;


import com.codegym.model.Role;
import com.codegym.repository.IRoleRepository;
import com.codegym.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public Optional<Role> roleAdmin() {
        return iRoleRepository.roleAdmin();
    }


    @Override
    public Optional<Role> roleCustomer() {
        return iRoleRepository.roleCustomer();
    }


    @Override
    public Optional<Role> roleEmployee() {
        return iRoleRepository.roleEmployee();
    }

}
