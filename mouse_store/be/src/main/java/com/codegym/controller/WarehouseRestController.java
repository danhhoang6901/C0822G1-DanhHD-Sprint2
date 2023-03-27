package com.codegym.controller;

import com.codegym.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("warehouse")
public class WarehouseRestController {
    @Autowired
    private IWareHouseService wareHouseService;

    @PutMapping("{id}&{quantityNew}")
    public ResponseEntity<?> wareHousing(@PathVariable("id") Integer id, @PathVariable("quantityNew") int quantityNew) {
        wareHouseService.wareHousing(id, quantityNew);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
