package com.codegym.service.impl;

import com.codegym.model.Payment;
import com.codegym.repository.IPaymentRepository;
import com.codegym.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepository.getPaymentById(id);
    }
}
