package com.example.demo.service.impl;

import com.example.demo.model.SizeEntity;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public List<SizeEntity> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public void save(SizeEntity size) {
        sizeRepository.save(size);
    }

    @Override
    public void saveProductSize(Integer productId, String sizeId) {
        sizeRepository.saveProductSize(productId, sizeId);
    }

    @Override
    public List<Integer>  findSizeProduct(Integer productId) {
        return sizeRepository.findSizeProduct(productId);
    }

    @Override
    public void deleteSizeFromProductId(Integer productId) {
        sizeRepository.deleteSizeFromProductId(productId);
    }
}
