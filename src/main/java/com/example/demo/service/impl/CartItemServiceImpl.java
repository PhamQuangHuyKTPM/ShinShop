package com.example.demo.service.impl;

import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItemEntity findFirstByCartOrderByIdDesc(CartEntity cart) {
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findFirstByCartOrderByIdDesc(cart);
        return optionalCartItem.orElse(null);
    }

    @Override
    public CartItemEntity findById(Integer id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void deleteAllByCartId(Long carId) {
        cartItemRepository.deleteAllByCartId(carId);
    }
}
