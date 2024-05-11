package com.example.demo.api;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.*;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-cart")
public class CartAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<?> getShoppingCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok("null");
        }

        String username = principal.getName();
        UserEntity user = userService.findByUserName(username);
        CartEntity shoppingCart = user.getCart();
        if (shoppingCart == null) {
            return ResponseEntity.ok("null");
        }

        return ResponseEntity.ok(shoppingCart.getCartItems());
    }


    @PostMapping("/add-item-to-cart")
    public ResponseEntity<?> addItemToCart(@RequestBody Integer productId, Principal principal){

        if (principal == null) {
            return ResponseEntity.ok("null");
        }

        int quantity = 1;
        String size = null;

        ProductEntity product = productService.findById(productId);
        String username = principal.getName();
        UserEntity user = userService.findByUserName(username);
        CartEntity cart = cartService.addItemToCart(product, quantity, user, size);

        CartItemEntity carItem = cartItemService.findFirstByCartOrderByIdDesc(cart);
        return ResponseEntity.ok(carItem);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deteteItemCart(@RequestBody String productId, Principal principal){
        if(principal == null){
            return ResponseEntity.ok("null");
        }else{
            String username = principal.getName();
            UserEntity user = userService.findByUserName(username);
            ProductEntity product = productService.findById(Integer.parseInt(productId));

            CartEntity cart = cartService.deleteItemInCart(product, user);

            return ResponseEntity.ok(cart);
        }
    }

    @PostMapping("/cartItem/find/{id}")
    public ResponseEntity<?> findItemCart(@PathVariable("id") Integer id){
            CartItemEntity cartItem = cartItemService.findById(id);

            return ResponseEntity.ok(cartItem);
    }

    @PostMapping("/find/{id}")
    public ResponseEntity<?> findCart(@PathVariable("id") Integer id){
        CartEntity cart = cartService.findById(id);

        return ResponseEntity.ok(cart);
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateItemInCart(@RequestParam("quantity") int quantity, @RequestParam("id") Integer productId
            , Principal principal){
        if(principal == null){
            return ResponseEntity.ok("null");
        }else{
            String username = principal.getName();
            UserEntity user = userService.findByUserName(username);
            ProductEntity product = productService.findById(productId);
            CartEntity cart = cartService.updateItemInCart(product, quantity, user);

            return ResponseEntity.ok(cart);
        }
    }

}
