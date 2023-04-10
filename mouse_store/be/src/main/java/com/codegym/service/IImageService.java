package com.codegym.service;

import com.codegym.model.Image;

import java.util.List;

public interface IImageService {
    void createImage(Image image);

    List<Image> showImageByIdProduct(Integer id);

    Image getImgUrlPruct(Integer id);

    void deleteImageById(Image image);
}
