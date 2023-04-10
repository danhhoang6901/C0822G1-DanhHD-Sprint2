package com.codegym.controller;

import com.codegym.model.Image;
import com.codegym.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("image")
public class ImageRestController {
    @Autowired
    private IImageService imageService;

    @GetMapping("{id}")
    public ResponseEntity<List<Image>> showImageByIdProduct(@PathVariable("id") Integer id) {
        List<Image> images = imageService.showImageByIdProduct(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Image> deleteImageById(@PathVariable("id") Integer id) {
        Image image = imageService.getImgUrlPruct(id);
        imageService.deleteImageById(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
