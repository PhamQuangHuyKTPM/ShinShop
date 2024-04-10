package com.example.demo.api;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CartEntity;
import com.example.demo.model.CartItemEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
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

    @PostMapping("/find")
    public ResponseEntity<?> findCartById(@RequestBody Integer id){
        UserEntity user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/add-item-to-cart")
    public ResponseEntity<?> addItemToCart(@RequestBody String id, Principal principal){
        int quantity = 1;
        if (principal == null) {
            return ResponseEntity.ok("null");
        }
        ProductEntity product = productService.findById(Integer.parseInt(id));
        String username = principal.getName();
        UserEntity user = userService.findByUserName(username);
        CartEntity cart = cartService.addItemToCart(product, quantity, user);

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
