package com.example.demo.repository;

import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findFirstByCartOrderByIdDesc(CartEntity cart);

    CartItemEntity findById(Integer id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `cart-item` WHERE cart_id = :carId", nativeQuery = true)
    void deleteAllByCartId(Long carId);
}
