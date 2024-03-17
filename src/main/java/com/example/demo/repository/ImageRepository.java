package com.example.demo.repository;

import com.example.demo.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    Set<ImageEntity> findAllByOrderByIdDesc();

    boolean existsByImageName(String imageName);
    boolean findFirstByImageName(String name);
}
