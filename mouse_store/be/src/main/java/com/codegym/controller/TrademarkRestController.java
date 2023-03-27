package com.codegym.controller;

import com.codegym.model.Trademark;
import com.codegym.service.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("trademark")
public class TrademarkRestController {
    @Autowired
    private ITrademarkService trademarkService;

    @GetMapping("")
    public ResponseEntity<List<Trademark>> getAllTrademark() {
        List<Trademark> trademarks = trademarkService.getAllTrademark();
        if (trademarks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trademarks, HttpStatus.OK);
    }
}
