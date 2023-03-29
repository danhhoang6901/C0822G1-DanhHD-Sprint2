package com.codegym.controller;

import com.codegym.dto.BillDto;
import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.model.Payment;
import com.codegym.model.Product;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("bill")
public class BillRestController {
    @Autowired
    private IBillService billService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @GetMapping("cart/{id}")
    public ResponseEntity<List<BillDetail>> getListProductDetailByUserId(@PathVariable("id") String id) {
        List<BillDetail> billDetails = billService.getCartByUserId(id);
        if (billDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }

    @PostMapping("addBill")
    public ResponseEntity<Bill> addBill(@RequestBody BillDto billDto) {

        Bill bill = billService.getBill(billDto.getUser());
        if (bill == null) {
            Payment payment = new Payment();
            payment.setPaymentStatus(false);
            paymentService.addPayment(payment);

            Bill bill1 = new Bill();
            bill1.setPayment(payment);
            bill1.setUser(userService.getUserById(billDto.getUser()));
            billService.addBill(bill1);

            Bill bill2 = billService.getBill(billDto.getUser());
            BillDetail billDetail = new BillDetail();
            billDetail.setBill(bill2);
            billDetail.setProduct(productService.findById(billDto.getProduct()));
            billDetail.setQuantity(billDto.getQuantity());
            billService.addBillDetail(billDetail);

            return new ResponseEntity<>(bill2, HttpStatus.OK);
        }

        BillDetail billDetail = new BillDetail();
        Product product = productService.findById(billDto.getProduct());
        List<BillDetail> billDetails = billService.getCartByUserId(String.valueOf(billDto.getUser()));

        for (BillDetail x : billDetails) {
            if (x.getProduct().getId() == product.getId()) {
                x.setQuantity(x.getQuantity() + billDto.getQuantity());
                billService.addBillDetail(x);
                return new ResponseEntity<>(bill, HttpStatus.OK);
            }
        }
        billDetail.setBill(bill);
        billDetail.setProduct(product);
        billDetail.setQuantity(billDto.getQuantity());
        billService.addBillDetail(billDetail);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}
