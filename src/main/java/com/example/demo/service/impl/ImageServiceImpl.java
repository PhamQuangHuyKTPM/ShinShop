package com.example.demo.service.impl;

import com.example.demo.controller.admin.ImageController;
import com.example.demo.model.ImageEntity;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Set<ImageController> getAll() {
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


}
