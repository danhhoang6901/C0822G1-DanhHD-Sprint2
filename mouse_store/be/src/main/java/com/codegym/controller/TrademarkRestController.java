package com.codegym.controller;

import com.codegym.service.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("trademark")
public class TrademarkRestController {
    @Autowired
    private ITrademarkService trademarkService;
}
