package com.example.demo.controller.web;


import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderDetail/{id}")
    public String orderDetail(@PathVariable("id") Integer id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/home/login";
        }
        OrderEntity order = orderService.findById(id);
        model.addAttribute("order", order);
        return "web/pages/order-detail";
    }

    @GetMapping("/orderDetail/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/home/login";
        }
        orderService.updateStatusById(id, "Hủy đơn");

        return "redirect:/home/order-history";
    }



}
