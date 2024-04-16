package com.example.demo.service;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    CartEntity addItemToCart(ProductEntity product, int quantity, UserEntity user, String size);

    CartEntity updateItemInCart(ProductEntity product, int quantity, UserEntity user);

    CartEntity deleteItemInCart(ProductEntity product, UserEntity user);

    CartEntity findById(Integer id);

    void save(CartEntity cart);
}
