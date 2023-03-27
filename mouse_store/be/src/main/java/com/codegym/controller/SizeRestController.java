package com.codegym.controller;

import com.codegym.model.Size;
import com.codegym.service.ISizeService;
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
@RequestMapping("size")
public class SizeRestController {
    @Autowired
    private ISizeService sizeService;

    @GetMapping("")
    public ResponseEntity<List<Size>> getAllSize(){
        List<Size> sizes = sizeService.getAllSize();
        if (sizes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sizes,HttpStatus.OK);
    }
}
