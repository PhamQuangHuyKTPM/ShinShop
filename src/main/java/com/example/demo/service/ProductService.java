package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();

    Boolean create(ProductEntity product);
    CategoryEntity findById(Integer id);
    Boolean update (ProductEntity product);
    Boolean delete(Integer id);
}
