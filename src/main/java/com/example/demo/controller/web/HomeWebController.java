package com.example.demo.controller.web;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeWebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("")
    public String index(Model model, Principal principal, HttpSession session) {
        if(principal != null){
            session.setAttribute("username", principal.getName());
            UserEntity user = userService.findByUserName(principal.getName());
            CartEntity cart = user.getCart();
            session.setAttribute("totalItems", cart.getTotalItems());
        }else{
            session.removeAttribute("username");
        }
        model.addAttribute("productList", productService.findAll());
        return "web/index";
    }

    @GetMapping("/login")
    public String formLogin(){
        return "web/login";
    }

    @PostMapping("/register")
    public String registryAccount(@ModelAttribute("user") UserEntity user){
        String pass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pass);
        user.setEnable(true);
        userService.save(user);
        userService.saveUserRole(2, user.getId());
        return "web/login";
    }

    @GetMapping("/checkout")
    public String checkoutPage(Principal principal, Model model){
        if (principal == null){
            return "redirect:/home/login";
        }
        UserEntity user = userService.findByUserName(principal.getName());
        CartEntity cart = user.getCart();
        model.addAttribute("user", user);
        model.addAttribute("cart", cart);
        return "web/pages/shop-checkout";
    }

    @GetMapping("/user-details")
    public String userDetail(Principal principal, Model model){
        if(principal == null) return "redirect:/home/login";

        UserEntity user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "web/pages/user-details";
    }

    @GetMapping("/order-history")
    public String orderHistory(Principal principal, Model model){
        if(principal == null) return "redirect:/home/login";
        List<OrderEntity> orders = orderService.findAll();
        model.addAttribute("order", orders);
        return "web/pages/order-history";
    }

    @GetMapping("/checkoutOrder")
    public String checkout(Principal principal,@RequestParam("username") String username ,
                           @RequestParam("nodeCheckout") String noteCheckout,
                            Model model){
        if(principal == null) return "redirect:/home/login";

        System.out.println(username);
        UserEntity user = userService.findByUserName(username);
        CartEntity cart = user.getCart();
        Long orderNewId =  orderService.addOrder(cart, noteCheckout);
        cartItemService.deleteAllByCartId(cart.getId());

        OrderEntity order = orderService.findById(orderNewId);
        model.addAttribute("order", order);
        return "web/pages/order-detail";
    }
}
