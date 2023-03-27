package com.codegym.controller;

import com.codegym.service.IBillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("billHistory")
public class BillHistoryRestController {
    @Autowired
    private IBillHistoryService billHistoryService;
}
