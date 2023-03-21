package com.codegym.service.impl;

import com.codegym.repository.IWareHouseRepository;
import com.codegym.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WareHouseService implements IWareHouseService {
    @Autowired
    private IWareHouseRepository wareHouseRepository;
}
