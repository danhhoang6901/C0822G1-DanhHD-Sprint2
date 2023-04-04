package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillService {
    List<BillDetail> getCartByUserId(String id);

    Bill getBill(Integer user);

    void addBill(Bill bill1);

    void addBillDetail(BillDetail billDetail);

    BillDetail getBillDetail(Integer id);

    BillDetail findById(Integer id);

    void deleteProducInCart(Integer id);

    Bill findBillById(Integer id);

    Page<Bill> showBill(Pageable pageable, Integer id);

    List<Bill> showAllBill();
}
