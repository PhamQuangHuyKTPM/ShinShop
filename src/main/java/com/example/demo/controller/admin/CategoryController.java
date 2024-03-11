package com.example.demo.controller.admin;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model){
        List<CategoryEntity> list = categoryService.findAll();

        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model){
        CategoryEntity category = new CategoryEntity();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "admin/category/add-category";
    }

    /*@PostMapping("/add-category")
    public String save(@ModelAttribute("category") CategoryEntity category, @Valid @RequestBody CategoryDTO categoryDTO){
        if(this.categoryService.create(category)){
            return "redirect:/admin/category";
        }else{
            return "admin/category/add-category";
        }
    }*/

    @PostMapping("/add-category")
    public String save(@Valid @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Trả về thông báo lỗi nếu dữ liệu không hợp lệ
            return "admin/category/add-category";
        }
        else{
            categoryService.create(categoryDTO);
            return "redirect:/admin/category";
        }
    }
    @GetMapping("/edit-category/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model){
        CategoryEntity category = categoryService.findById(id);
        category.setId(id);
        model.addAttribute("category", category);
        System.err.println("id = " + category.getId());
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String update(@Valid @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            // Trả về thông báo lỗi nếu dữ liệu không hợp lệ

            model.addAttribute("category", categoryDTO);
            return "admin/category/edit";
        }
        else{
            categoryService.update(categoryDTO);
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/admin/category";
    }



}
