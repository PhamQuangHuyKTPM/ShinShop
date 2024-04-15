package com.example.demo.api;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.ProductService;
import com.example.demo.validator.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(){
        try {
            List<ProductEntity> products = productService.findAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Integer id){
        try {
            ProductEntity product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity product){

        try{
            product.setModifiedDate(LocalDate.now());
            CustomUserDetails customUserDetails = new CustomUserDetails();
            product.setModifiedBy(customUserDetails.getCurrentUsername());
            System.out.println(product.getImage());
            productService.update(product);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(product);
    }


    @PostMapping("/validate")
    public Map<String, Object> validate(@Valid @RequestBody ProductDTO product, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> fieldErrors = new HashMap<>();
            for (FieldError error : errors) {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("fieldErrors", fieldErrors);
        } else {
            // Trường hợp không có lỗi, bạn có thể thực hiện các thao tác khác ở đây
            response.put("successMessage", "Validation successful");
        }
        // Thêm dữ liệu DTO vào phản hồi
        response.put("data", product);
        return response;
    }
}
