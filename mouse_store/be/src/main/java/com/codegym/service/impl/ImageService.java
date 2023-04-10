package com.codegym.service.impl;

import com.codegym.model.Image;
import com.codegym.repository.IImageRepository;
import com.codegym.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public void createImage(Image image) {
        imageRepository.createImage(image);
    }

    @Override
    public List<Image> showImageByIdProduct(Integer id) {
        return imageRepository.showImageByIdProduct(id);
    }

    @Override
    public Image getImgUrlPruct(Integer id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImageById(Image image) {
        imageRepository.delete(image);
    }
}
