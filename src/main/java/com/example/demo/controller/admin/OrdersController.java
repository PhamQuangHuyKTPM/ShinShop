package com.example.demo.controller.admin;

import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String ordersPage(Principal principal, Model model){
        List<OrderEntity> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders/order-manager";
    }

    @GetMapping("/order-detail/{id}")
    public String orderDetail(@PathVariable("id") Long id, Model model){
        OrderEntity order = orderService.findById(id);
        model.addAttribute("order", order);
        return "admin/orders/order-detail";
    }


    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("statusName") String statusName, @RequestParam("id") Long id){
        orderService.updateStatusById(id, statusName);
        return "redirect:/admin/orders";
    }


    @GetMapping("/editStatus/{id}")
    public String formUpdateStatus(@PathVariable("id") Long id, Principal principal, Model model){
        List<String> statusList = new ArrayList<>();
        OrderEntity order = orderService.findById(id);
        statusList.add("Đang chờ xác nhận");
        statusList.add("Đang giao hàng");
        statusList.add("Hủy đơn");
        statusList.add("Hoàn thành");
        model.addAttribute("listStatus", statusList);
        model.addAttribute("order", order);
        return "admin/orders/updateStatus";
    }


    @GetMapping("/edit/{id}")
    public String acceptStatus(@PathVariable("id") Long id){
        orderService.updateStatusById(id, "Đang giao hàng");
        return "redirect:/admin/orders";
    }


}