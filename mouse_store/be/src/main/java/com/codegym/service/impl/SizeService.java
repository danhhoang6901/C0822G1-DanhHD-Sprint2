package com.codegym.service.impl;

import com.codegym.model.Size;
import com.codegym.repository.ISizeRepository;
import com.codegym.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;

    @Override
    public Set<Size> getAllSize() {
        return sizeRepository.getAllSize();
    }

    @Override
    public Optional<Size> sizeM() {
        return sizeRepository.sizeM();
    }

    @Override
    public Optional<Size> sizeL() {
        return sizeRepository.sizeL();
    }

    @Override
    public Optional<Size> sizeXL() {
        return sizeRepository.sizeXL();
    }

    @Override
    public Optional<Size> sizeS() {
        return sizeRepository.sizeS();
    }
}
