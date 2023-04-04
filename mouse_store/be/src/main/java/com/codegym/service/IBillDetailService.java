package com.codegym.service;

import com.codegym.dto.IBillDetailDto;
import com.codegym.model.BillDetail;

import java.util.List;

public interface IBillDetailService {
    void addBillDetail(BillDetail billDetail);

    List<IBillDetailDto> showBillDetail(Integer id);
}
