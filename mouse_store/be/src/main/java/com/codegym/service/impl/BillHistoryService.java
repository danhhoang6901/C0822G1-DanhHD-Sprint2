package com.codegym.service.impl;

import com.codegym.repository.IBillHistoryRepository;
import com.codegym.service.IBillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillHistoryService implements IBillHistoryService {
    @Autowired
    private IBillHistoryRepository billHistoryRepository;
}
