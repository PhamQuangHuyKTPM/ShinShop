package com.example.demo.repository;

import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findFirstByCartOrderByIdDesc(CartEntity cart);

}
