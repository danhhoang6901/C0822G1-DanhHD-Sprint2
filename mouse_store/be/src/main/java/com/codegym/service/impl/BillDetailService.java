package com.codegym.service.impl;

import com.codegym.model.BillDetail;
import com.codegym.repository.IBillDetailRepository;
import com.codegym.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Override
    public void addBillDetail(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }
}
