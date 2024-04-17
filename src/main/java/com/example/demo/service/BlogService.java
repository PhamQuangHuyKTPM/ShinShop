package com.example.demo.service;

import com.example.demo.model.BlogEntity;

import java.util.List;

public interface BlogService {

    void create(BlogEntity blog);

    List<BlogEntity> getAllBlog();

    BlogEntity findById(Integer id);
}
