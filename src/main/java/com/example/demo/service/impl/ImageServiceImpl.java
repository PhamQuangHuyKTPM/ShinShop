package com.example.demo.service.impl;

import com.example.demo.controller.admin.ImageController;
import com.example.demo.dto.ImageDTO;
import com.example.demo.model.ImageEntity;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Set<ImageDTO> getAll() {
        return null;
    }

    @Override
    public void save(ImageEntity img) {
        imageRepository.save(img);
    }

    @Override
    public Set<ImageEntity> findAllByOrderByIdDesc() {
        return imageRepository.findAllByOrderByIdDesc();
    }

    @Override
    public boolean existsByImageName(String imageName) {
        return imageRepository.existsByImageName(imageName);
    }

    @Override
    public boolean findFirstByImageName(String name) {
        if(imageRepository.findFirstByImageName(name)){
            return true;
        }
        else return false;
    }

    @Override
    public ImageEntity saveImage(ImageEntity imageEntity) {
        return imageRepository.save(imageEntity);
    }

    @Override
    public ImageEntity findByImageName(String imageName) {
        return imageRepository.findByImageName(imageName);
    }


}
