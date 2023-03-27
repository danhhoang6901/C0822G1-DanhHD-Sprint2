package com.codegym.service.impl;

import com.codegym.model.Size;
import com.codegym.repository.ISizeRepository;
import com.codegym.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.getAllSize();
    }
}
