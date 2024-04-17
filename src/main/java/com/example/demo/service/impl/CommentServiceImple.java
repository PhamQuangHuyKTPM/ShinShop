package com.example.demo.service.impl;

import com.example.demo.model.CommentEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImple implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addNewCommentProduct(CommentEntity comment, String rating, String content) {
        if(rating == null || rating == ""){
            comment.setRating(0);
        }else{
            comment.setRating(Integer.parseInt(rating));
        }
        comment.setContent(content);
        comment.setDate(LocalDate.now());
        commentRepository.save(comment);
    }

    @Override
    public List<CommentEntity> findByProduct(ProductEntity product) {
        return commentRepository.findByProduct(product);
    }
}
