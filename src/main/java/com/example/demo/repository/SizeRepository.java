package com.example.demo.repository;

import com.example.demo.model.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product_size (product_id, size_id) VALUES (:productId, :sizeId)", nativeQuery = true)
    void saveProductSize(Integer productId, String sizeId);

    @Query(value = "SELECT size_id FROM product_size WHERE product_id= :productId", nativeQuery = true)
    List<Integer> findSizeProduct(Integer productId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM product_size  WHERE product_id= :productId", nativeQuery = true)
    void deleteSizeFromProductId(Integer productId);
}
