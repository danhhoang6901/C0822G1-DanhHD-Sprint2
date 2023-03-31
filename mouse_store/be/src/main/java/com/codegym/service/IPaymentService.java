package com.codegym.service;

import com.codegym.model.Payment;

public interface IPaymentService {
    void addPayment(Payment payment);

    Payment getPaymentById(Integer id);
}
