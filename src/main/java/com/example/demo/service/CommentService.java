package com.example.demo.service;

import com.example.demo.model.CommentEntity;
import com.example.demo.model.ProductEntity;

import java.util.List;

public interface CommentService {

    void addNewCommentProduct(CommentEntity comment, String rating, String content);

    List<CommentEntity> findByProduct(ProductEntity product);
}
