package com.example.demo.service;

import com.example.demo.model.CartEntity;
import com.example.demo.model.OrderEntity;

import java.util.List;

public interface OrderService {
    Long addOrder(CartEntity cart, String node);
    OrderEntity findById(Long id);

    OrderEntity findById(Integer id);

    List<OrderEntity> findAll();
}
