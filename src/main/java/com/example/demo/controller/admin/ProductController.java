package com.example.demo.controller.admin;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SizeService;
import com.example.demo.service.impl.FileSystemStorageService;
import com.example.demo.validator.ProductValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
    private SizeService sizeService;

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping("/create")
    public String createPage(Model model){
        List<CategoryEntity> category = categoryService.findAll();
        model.addAttribute("category",category);

        ProductEntity product = new ProductEntity();
        model.addAttribute("product", product);

        List<SizeEntity> sizes = sizeService.findAll();
        model.addAttribute("sizes", sizes);
        return "admin/product/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/admin/product";
    }


    @PostMapping("/create")
    public String saveProduct(@Valid @ModelAttribute("product") ProductEntity product, @RequestParam("image_name") MultipartFile image_name
                              , @RequestParam("selectedImages") String gallery, @RequestParam( value = "sizes", required = false) List<String> sizes
                              , BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            List<CategoryEntity> category = categoryService.findAll();
            model.addAttribute("category",category);
            List<SizeEntity> size = sizeService.findAll();
            model.addAttribute("sizes", size);
            return "admin/product/create";
        }else{

            try{
                product.setCreatedDate(LocalDate.now());
                CustomUserDetails customUserDetails = new CustomUserDetails();
                product.setCreatedBy(customUserDetails.getCurrentUsername());
                storageService.store(image_name);
                product.setImage(image_name.getOriginalFilename());

                System.out.println("__________________" + product.getCategory() + "______________________");


                Integer newIdProduct = productService.create(product);

                // Thêm gallery
                if(!gallery.isEmpty()){
                    //  chuyển đổi list gallery sang list Integer
                    ObjectMapper objectMapper = new ObjectMapper();
                    Integer[] images = objectMapper.readValue(gallery, Integer[].class);
                    for (Integer imageId : images) {
                        productService.saveProductImage(newIdProduct, imageId);
                    }
                }

                // Thêm size
                if(sizes != null){
                    for(String size : sizes){
                        sizeService.saveProductSize(newIdProduct, size);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "redirect:/admin/product";
    }

    @GetMapping("")
    public String homePage(Model model){
        List<Object[]> products = productService.findAllWidthCategoryName();
        model.addAttribute("products", products);
        return "admin/product/index";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Integer id, Model model){
        ProductEntity product = productService.findById(id);
        List<Integer> findSize = sizeService.findSizeProduct(id);

        model.addAttribute("product", product);
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("findSize", findSize);

        return "admin/product/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") ProductEntity product,@RequestParam("image_name") MultipartFile image_name
            , @RequestParam("selectedImages") String gallery, HttpServletRequest request
            , @RequestParam( value = "sizes", required = false) List<String> sizes){

        try{

            product.setModifiedDate(LocalDate.now());
            CustomUserDetails customUserDetails = new CustomUserDetails();
            product.setModifiedBy(customUserDetails.getCurrentUsername());
            System.out.println(product.getName());

            if(!image_name.isEmpty()){
                storageService.store(image_name);
                product.setImage(image_name.getOriginalFilename());
            }
            productService.update(product);

            sizeService.deleteSizeFromProductId(product.getId());
            if(sizes != null){
               for(String size : sizes){
                   sizeService.saveProductSize(product.getId(), size);
                   System.out.println(size);
               }
            }

            if(!gallery.isEmpty()){

                productService.deleteGalleryFromProductId(product.getId());

                //  chuyển đổi list gallery sang list Integer
                ObjectMapper objectMapper = new ObjectMapper();
                Integer[] images = objectMapper.readValue(gallery, Integer[].class);

                for (Integer imageId : images) {
                    productService.saveProductImage(product.getId(), imageId);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin/product";
    }

}
