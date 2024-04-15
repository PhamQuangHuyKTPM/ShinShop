package com.example.demo.service;

import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;

public interface CartItemService {
    CartItemEntity findFirstByCartOrderByIdDesc(CartEntity cart);

    CartItemEntity findById(Integer id);

    void deleteAllByCartId(Long carId);
}
