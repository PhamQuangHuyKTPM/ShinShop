package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductEntity> findAll() {
        return null;
    }

    @Override
    public Boolean create(ProductEntity product) {
        try{
            productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CategoryEntity findById(Integer id) {
        return null;
    }

    @Override
    public Boolean update(ProductEntity product) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
