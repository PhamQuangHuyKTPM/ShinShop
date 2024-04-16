package com.example.demo.api;

import com.example.demo.model.OrderDetailsEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity<?> findItemCart(@PathVariable("id") Integer id){
        OrderEntity order = orderService.findById(id);
        if(order == null){
            System.out.println("null");
        }
        OrderDetailsEntity orderdetail = order.getOrderDetails().get(0);
        for(OrderDetailsEntity o : order.getOrderDetails()){
            System.out.println(o.getProduct().getName());
        }

        return ResponseEntity.ok(order);
    }

    @GetMapping("")
    public ResponseEntity<?> findAlla(){
        List<OrderEntity> orders = orderService.findAll();
        orders.get(0).getOrderDetails();
        for(OrderDetailsEntity o : orders.get(0).getOrderDetails()){
            System.out.println(o.getProduct().getName());
        }
        return ResponseEntity.ok(orders);
    }


}
