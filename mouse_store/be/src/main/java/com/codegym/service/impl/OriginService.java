package com.codegym.service.impl;

import com.codegym.repository.IOriginRepository;
import com.codegym.service.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginService implements IOriginService {
    @Autowired
    private IOriginRepository originRepository;
}
