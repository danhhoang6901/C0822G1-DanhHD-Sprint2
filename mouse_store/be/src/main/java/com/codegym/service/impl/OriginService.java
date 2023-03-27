package com.codegym.service.impl;

import com.codegym.model.Origin;
import com.codegym.repository.IOriginRepository;
import com.codegym.service.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService implements IOriginService {
    @Autowired
    private IOriginRepository originRepository;

    @Override
    public List<Origin> getAllOrigin() {
        return originRepository.getAllOrigin();
    }
}
