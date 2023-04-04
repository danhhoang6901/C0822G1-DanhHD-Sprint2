package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("{id}")
    public ResponseEntity<Page<Bill>> showBill(@PageableDefault(size = 3) Pageable pageable, @PathVariable("id") Integer id) {
        Page<Bill> bills = billService.showBill(pageable, id);
        if (bills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Bill> findBillById(@PathVariable("id") Integer id) {
        Bill bill = billService.findBillById(id);
        if (bill == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Bill>> showAllBill(){
        List<Bill> bills = billService.showAllBill();
        return new ResponseEntity<>(bills,HttpStatus.OK);
    }
}
