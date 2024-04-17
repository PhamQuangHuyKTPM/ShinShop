package com.example.demo.controller.web;

import com.example.demo.model.BlogEntity;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/blog")
public class BlogWebController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog-detail/{id}")
    public String showBlogDetail(@PathVariable("id") Integer id, Model model){
        BlogEntity blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "web/pages/blog-detail";
    }

    @GetMapping("/blog-list")
    public String blogList(Model model){
        List<BlogEntity> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "web/pages/blog-list";
    }

}
