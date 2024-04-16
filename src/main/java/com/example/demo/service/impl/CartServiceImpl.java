package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartEntity addItemToCart(ProductEntity product, int quantity, UserEntity user, String size) {

        CartEntity cart = user.getCart();
        if(cart == null){
            cart = new CartEntity();
        }

        Set<CartItemEntity> cartItems = cart.getCartItems();
        CartItemEntity cartItem = findCartItem(cartItems, product.getId());
        if(cartItems == null){
            cartItems = new HashSet<>();
            if(cartItem == null){
                cartItem = new CartItemEntity();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                if(size == null){
                    if(product.getSize().iterator().next() == null){
                        cartItem.setSize(null);
                    }else {
                        cartItem.setSize(product.getSize().iterator().next().getSizeName());
                    }
                }
                else{
                    cartItem.setSize(size);
                }

                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            }
        }else{
            if(cartItem == null){
                cartItem = new CartItemEntity();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                if(size == null){
                    if(product.getSize().iterator().next() == null){
                        cartItem.setSize(null);
                    }else {
                        cartItem.setSize(product.getSize().iterator().next().getSizeName());
                    }
                }
                else{
                    cartItem.setSize(size);
                }
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            }else{
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getPrice()));
                cartItemRepository.save(cartItem);
            }
        }
        cart.setCartItems(cartItems);

        int totalItems = totalItems(cart.getCartItems());
        double totalPrice = totalPrice(cart.getCartItems());

        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public CartEntity updateItemInCart(ProductEntity product, int quantity, UserEntity user) {
        CartEntity cart = user.getCart();

        Set<CartItemEntity> cartItems = cart.getCartItems();
        CartItemEntity item = findCartItem(cartItems, product.getId());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());
        cartItemRepository.save(item);

        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrice(cartItems);

        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public CartEntity deleteItemInCart(ProductEntity product, UserEntity user) {
        CartEntity cart = user.getCart();

        Set<CartItemEntity> cartItems = cart.getCartItems();
        CartItemEntity item = findCartItem(cartItems, product.getId());
        cartItems.remove(item);

        cartItemRepository.delete(item);

        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrice(cartItems);

        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public CartEntity findById(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(CartEntity cart) {
        cartRepository.save(cart);
    }

    private CartItemEntity findCartItem(Set<CartItemEntity> cartItems, Integer productId){
        if(cartItems == null){
            return null;
        }

        CartItemEntity cartItem = null;
        for(CartItemEntity item : cartItems){
            if(item.getProduct().getId() == productId){
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItemEntity> cartItems){
        int totalItems = 0;
        for(CartItemEntity item : cartItems){
            totalItems += item.getQuantity();
        }

        return totalItems;

    }

    private double totalPrice(Set<CartItemEntity> cartItems){
        double totalPrice = 0.0;
        for(CartItemEntity item : cartItems){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }


}
