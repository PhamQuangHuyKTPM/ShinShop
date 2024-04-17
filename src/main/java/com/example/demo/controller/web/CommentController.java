package com.example.demo.controller.web;

import com.example.demo.model.CommentEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.service.CommentService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/home/comment-product")
public class CommentController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public String addNewCommnentProduct(HttpServletRequest request, @RequestParam("id") Integer id,
                                        @RequestParam("rating") String rating,
                                        @RequestParam("content") String content,
                                        Principal principal){

        if(principal == null) return "redirect:/home/login";
        CommentEntity comment = new CommentEntity();

        UserEntity user = userService.findByUserName(principal.getName());
        ProductEntity product = productService.findById(id);
        comment.setProduct(product);
        comment.setUser(user);

        commentService.addNewCommentProduct(comment, rating, content);

        return "redirect:" + request.getHeader("Referer");
    }

}
