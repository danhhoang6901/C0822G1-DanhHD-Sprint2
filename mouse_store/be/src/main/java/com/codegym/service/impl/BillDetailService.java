package com.codegym.service.impl;

import com.codegym.repository.IBillDetailRepository;
import com.codegym.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private IBillDetailRepository billDetailRepository;
}