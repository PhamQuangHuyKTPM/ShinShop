package com.example.demo.service;

import com.example.demo.model.SizeEntity;

import java.util.List;

public interface SizeService {
    List<SizeEntity> findAll();

    void save(SizeEntity size);

    void saveProductSize(Integer productId, String sizeId);

    List<Integer> findSizeProduct(Integer productId);

    void deleteSizeFromProductId(Integer productId);
}
