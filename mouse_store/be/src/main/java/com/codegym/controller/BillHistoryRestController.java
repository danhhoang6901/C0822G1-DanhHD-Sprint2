package com.codegym.controller;

import com.codegym.dto.IBillDetailDto;
import com.codegym.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("billHistory")
public class BillHistoryRestController {
    @Autowired
    private IBillDetailService billHistoryService;

    @GetMapping("{id}")
    public ResponseEntity<List<IBillDetailDto>> showBillDetail(@PathVariable("id") Integer id) {
        List<IBillDetailDto> billDetails = billHistoryService.showBillDetail(id);
        if (billDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billDetails,HttpStatus.OK);
    }
}
