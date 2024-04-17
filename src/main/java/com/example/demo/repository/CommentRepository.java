package com.example.demo.repository;

import com.example.demo.model.CommentEntity;
import com.example.demo.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByProduct(ProductEntity product);
}
