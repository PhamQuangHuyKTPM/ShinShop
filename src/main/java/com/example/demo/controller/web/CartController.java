package com.example.demo.controller.web;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/home/shopping-cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String cartPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/home/login";
        }

        String username = principal.getName();
        UserEntity user = userService.findByUserName(username);
        CartEntity shoppingCart = user.getCart();
        if(shoppingCart == null){
            model.addAttribute("check", "No item in your cart");
        }
        model.addAttribute("shoppingCart", shoppingCart);
        return "web/pages/shopping-cart";
    }

    @GetMapping("/add-items-to-cart/{id}")
    public String addItemToCart(@PathVariable("id") Integer id, @PathVariable("size") String size, HttpServletRequest request,
                                Principal principal){
        int quantity = 1;

        if(principal == null){
            return "redirect:/home/login";
        }

        ProductEntity product = productService.findById(id);
        String username = principal.getName();
        UserEntity user = userService.findByUserName(username);

        CartEntity cart = cartService.addItemToCart(product, quantity, user, size);

        return "redirect:" + request.getHeader("Referer");
    }


    @PostMapping("/update")
    public String updateCart(@RequestParam("quantity") int quantity, @RequestParam("id") Integer productId,
                             Model model, Principal principal){
        if(principal == null){
            return "redirect:/home/login";
        }else{
            String username = principal.getName();
            UserEntity user = userService.findByUserName(username);
            ProductEntity product = productService.findById(productId);
            CartEntity cart = cartService.updateItemInCart(product, quantity, user);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/home/shopping-cart";
        }
    }

    @PostMapping("/delete")
    public String deleteItemInCart(@RequestParam("id") Integer productId,
                                   Model model, Principal principal){
        if(principal == null){
            return "redirect:/home/login";
        }else{
            String username = principal.getName();
            UserEntity user = userService.findByUserName(username);
            ProductEntity product = productService.findById(productId);
            CartEntity cart = cartService.deleteItemInCart(product, user);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/home/shopping-cart";
        }
    }


}
