package com.example.demo.controller.web;

import com.example.demo.model.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home/product-details")
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String page(@PathVariable("id") Integer id, Model model){

        ProductEntity product = productService.findById(id);
        if(product != null){
            Set<ProductEntity> relatedProducts = productService.findProductByCategory(product.getCategory());

            model.addAttribute("product", product);
            model.addAttribute("relatedProducts", relatedProducts);
        }

        return "web/pages/product-details";
    }

}
