package com.example.demo.service.impl;

import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;
import com.example.demo.model.OrderDetailsEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Long addOrder(CartEntity cart, String note) {
        OrderEntity order = new OrderEntity();
        order.setStatus("Đang chờ xác nhận");
        order.setPayMethod("Thanh toán tiền mặt");
        order.setNgayDat(LocalDate.now());
        order.setUser_order(cart.getUser());
        order.setGhiChu(note);
        order.setSubtotal(cart.getTotalPrices());
        order.setTotalPrices(cart.getTotalPrices() + 35000);
        List<OrderDetailsEntity> orderDetailList = new ArrayList<>();
        for(CartItemEntity item : cart.getCartItems()){
            OrderDetailsEntity orderDetail = new OrderDetailsEntity();
            orderDetail.setOrder(order);
            orderDetail.setSoluong(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setDongia(item.getProduct().getPrice());
            orderDetail.setTotalPrice(item.getTotalPrice());
            orderDetailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);

            cartItemRepository.delete(item);
        }

        order.setOrderDetails(orderDetailList);
        cart.setCartItems(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrices(0);
        cartRepository.save(cart);
        OrderEntity orderId  =  orderRepository.save(order);
        return orderId.getId();
    }

    @Override
    public OrderEntity findById(Long id) {
        Integer Id = id.intValue();
        return orderRepository.findById(Id);
    }

    @Override
    public OrderEntity findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }
}
