package com.example.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class CommentAPI {

    @PostMapping("/{id}")
    public ResponseEntity<?> findAllByProduct(){

        return ResponseEntity.ok("ok");
    }

}
