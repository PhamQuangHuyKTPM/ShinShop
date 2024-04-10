package com.example.demo.controller.web;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.service.ProductService;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeWebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

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


}
