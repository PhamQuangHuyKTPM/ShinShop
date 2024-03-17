package com.example.demo.controller.admin;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("")
    public String homePage(Model model){
        List<CategoryEntity> category = categoryService.findAll();
        model.addAttribute("category",category);

        ProductEntity product = new ProductEntity();
        model.addAttribute("product", product);
        return "admin/product/index";
    }

    @PostMapping("")
    public String saveProduct(@ModelAttribute("product") ProductEntity product,@RequestParam("image_name") MultipartFile image_name){

        product.setCreatedDate(LocalDate.now());
        CustomUserDetails customUserDetails = new CustomUserDetails();
        product.setCreatedBy(customUserDetails.getCurrentUsername());
        storageService.store(image_name);
        product.setImage(image_name.getOriginalFilename());

        productService.create(product);


        return "redirect:/admin/product";
    }

}
