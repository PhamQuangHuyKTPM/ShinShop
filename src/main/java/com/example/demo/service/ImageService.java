package com.example.demo.service;

import com.example.demo.controller.admin.ImageController;
import com.example.demo.dto.ImageDTO;
import com.example.demo.model.ImageEntity;

import java.util.List;
import java.util.Set;

public interface ImageService {
    Set<ImageDTO> getAll();

    void save(ImageEntity img);

    Set<ImageEntity> findAllByOrderByIdDesc();

    boolean existsByImageName(String imageName);
    boolean findFirstByImageName(String name);

    ImageEntity saveImage(ImageEntity imageEntity);

    ImageEntity findByImageName(String imageName);

}
