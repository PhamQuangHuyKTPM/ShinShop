package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean create(CategoryDTO categoryDto) {
        try{
            CategoryEntity category =new CategoryEntity();
            category.setCategoryName(categoryDto.getCategoryName());
            category.setCategoryStatus(categoryDto.getCategoryStatus());
            categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CategoryEntity findById(Integer id) {

        try{
            Optional<CategoryEntity> category = categoryRepository.findById(id);
            if(category.isPresent()){
                return category.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(CategoryDTO categoryDTO) {
        try{
            CategoryEntity category = new CategoryEntity();
            category.setId(categoryDTO.getId());
            category.setCategoryStatus(categoryDTO.getCategoryStatus());
            category.setCategoryName(categoryDTO.getCategoryName());
            categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            categoryRepository.delete(findById(id));
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CategoryEntity> findAllById(List<Integer> categoryId) {
        return categoryRepository.findAllById(categoryId);
    }


}
