package com.codegym.service.impl;

import com.codegym.repository.IStyleRepository;
import com.codegym.service.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StyleService implements IStyleService {
    @Autowired
    private IStyleRepository styleRepository;
}
