package com.example.demo.service;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;

public interface CartService {
    CartEntity addItemToCart(ProductEntity product, int quantity, UserEntity user);

    CartEntity updateItemInCart(ProductEntity product, int quantity, UserEntity user);

    CartEntity deleteItemInCart(ProductEntity product, UserEntity user);

}
