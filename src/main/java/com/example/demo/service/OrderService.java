package com.example.demo.service;

import com.example.demo.model.CartEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.model.UserEntity;

import java.util.List;

public interface OrderService {
    Long addOrder(CartEntity cart, String node);
    OrderEntity findById(Long id);

    OrderEntity findById(Integer id);

    List<OrderEntity> findAll();
    List<OrderEntity> findAllByUser_order(Integer id);

    void updateStatusById(Long id, String statusNew);
}
