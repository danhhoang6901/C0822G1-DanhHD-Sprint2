package com.codegym.service;

import com.codegym.model.Size;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ISizeService {
    Set<Size> getAllSize();

    Optional<Size> sizeM();
    Optional<Size> sizeL();
    Optional<Size> sizeXL();
    Optional<Size> sizeS();
}
