package com.codegym.controller;

import com.codegym.model.Origin;
import com.codegym.service.IOriginService;
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
@RequestMapping("origin")
public class OriginRestController {
    @Autowired
    private IOriginService originService;

    @GetMapping("")
    public ResponseEntity<List<Origin>> getAllOrigin() {
        List<Origin> origins = originService.getAllOrigin();
        if (origins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(origins, HttpStatus.OK);
    }
}
