package com.example.demo.api;

import com.example.demo.model.CartEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<?> getUser() {
        List<UserEntity> list = userService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> userDetail(Principal principal){
        if(principal == null){
            return ResponseEntity.ok("null");
        }

        return ResponseEntity.ok("ok");
    }

}
