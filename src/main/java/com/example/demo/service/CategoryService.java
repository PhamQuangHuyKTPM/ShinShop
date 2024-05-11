package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    Boolean create(CategoryDTO category);
    CategoryEntity findById(Integer id);
    Boolean update (CategoryDTO category);
    Boolean delete(Integer id);

    List<CategoryEntity> findAllById(List<Integer> categoryId);
}
