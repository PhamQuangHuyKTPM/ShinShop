package com.example.demo.repository;

import com.example.demo.model.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailsEntity, Long> {
}
