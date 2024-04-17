package com.example.demo.controller.admin;

import com.example.demo.model.BlogEntity;
import com.example.demo.service.BlogService;
import com.example.demo.service.impl.FileSystemStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String blogPage(Model model){
        List<BlogEntity> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "admin/blog/index";
    }

    @GetMapping("/blog-detail/{id}")
    public String blogDetail(@PathVariable("id") Integer id, Model model){
        BlogEntity blog =  blogService.findById(id);
        model.addAttribute("blog", blog);
        return "admin/blog/blog-detail";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("blog", new BlogEntity());
        return "admin/blog/create";
    }

    @PostMapping("/create")
    public String handleCreate(@ModelAttribute("blog") BlogEntity blog ,
                               @RequestParam("image_name") MultipartFile image_name,
                               Model model, Principal principal){
        if(principal == null){
            return "redirect/home/login";
        }

        storageService.store(image_name);

        blog.setCreaetedDate(LocalDate.now());
        blog.setCreatedBy(principal.getName());
        blog.setImage(image_name.getOriginalFilename());
        blogService.create(blog);
        return "redirect:/admin/blog/create";
    }




}
