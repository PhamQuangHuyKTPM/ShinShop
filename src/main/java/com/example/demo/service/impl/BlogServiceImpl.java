package com.example.demo.service.impl;

import com.example.demo.model.BlogEntity;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public void create(BlogEntity blog) {
        blogRepository.save(blog);
    }

    @Override
    public List<BlogEntity> getAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public BlogEntity findById(Integer id) {
        return blogRepository.findById(id);
    }
}
