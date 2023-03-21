package com.codegym.service.impl;

import com.codegym.repository.ISizeRepository;
import com.codegym.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;
}
