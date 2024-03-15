package com.example.demo.api;

import com.example.demo.model.CategoryEntity;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<CategoryEntity> getAllCategory(@RequestBody(required = false) List<CategoryEntity> model){
        return categoryService.findAll();
    }

    @PostMapping("")
    public CategoryEntity save(@RequestBody CategoryEntity category){
        return category;
    }

}
