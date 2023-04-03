package com.codegym.service.impl;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.repository.IBillDetailRepository;
import com.codegym.repository.IBillRepository;
import com.codegym.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> getCartByUserId(String id) {
        return billDetailRepository.getCartByUserId(id);
    }

    @Override
    public Bill getBill(Integer user) {
        return billRepository.getBill(user);
    }

    @Override
    public void addBill(Bill bill1) {
        billRepository.save(bill1);
    }

    @Override
    public void addBillDetail(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public BillDetail getBillDetail(Integer id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public BillDetail findById(Integer id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProducInCart(Integer id) {
        billDetailRepository.deleteById(id);
    }

    @Override
    public Bill findBillById(Integer id) {
        return billRepository.findById(id).orElse(null);
    }

//    @Override
//    public void deleteBill(Bill bill) {
//        billRepository.deleteBill(bill);
//    }
}
