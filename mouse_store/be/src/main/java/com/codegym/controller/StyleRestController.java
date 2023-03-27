package com.codegym.controller;

import com.codegym.service.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("style")
public class StyleRestController {
    @Autowired
    private IStyleService styleService;
}
