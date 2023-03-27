package com.codegym.controller;

import com.codegym.model.Style;
import com.codegym.service.IStyleService;
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
@RequestMapping("style")
public class StyleRestController {
    @Autowired
    private IStyleService styleService;

    @GetMapping("")
    public ResponseEntity<List<Style>> getAllStyle() {
        List<Style> styles = styleService.getAllStyle();
        if (styles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }
}
