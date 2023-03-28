package com.codegym.service.impl;

import com.codegym.model.Image;
import com.codegym.repository.IImageRepository;
import com.codegym.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public void createImage(Image image) {
        imageRepository.createImage(image);
    }
}
