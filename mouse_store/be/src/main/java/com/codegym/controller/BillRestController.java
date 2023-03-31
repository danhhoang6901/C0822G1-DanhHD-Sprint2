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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("minus/{id}")
    public ResponseEntity<BillDetail> minusQuantity(@PathVariable("id") Integer id) {
        BillDetail billDetail = billService.getBillDetail(id);
        billDetail.setQuantity(billDetail.getQuantity() - 1);
        billService.addBillDetail(billDetail);
        return new ResponseEntity<>(billDetail, HttpStatus.OK);
    }

    @GetMapping("plus/{id}")
    public ResponseEntity<BillDetail> plusQuantity(@PathVariable("id") Integer id) {
        BillDetail billDetail = billService.getBillDetail(id);
        billDetail.setQuantity(billDetail.getQuantity() + 1);
        billService.addBillDetail(billDetail);
        return new ResponseEntity<>(billDetail, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<BillDetail> deleteProducInCart(@PathVariable("id") Integer id) {
        BillDetail billDetail = billService.findById(id);
        if (billDetail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        billService.deleteProducInCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("payment/{id}")
    public ResponseEntity<Payment> payment(@PathVariable("id") Integer id, @RequestParam String note) {
        Payment payment = paymentService.getPaymentById(id);
        payment.setPaymentStatus(true);
        payment.setDatePurchase(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (note.length() == 0) {
            payment.setDescription("Không có ghi chú");
        } else {
            payment.setDescription(note);
        }
        paymentService.addPayment(payment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
