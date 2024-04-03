package com.example.demo.validator;

import com.example.demo.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO product = (ProductDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.name", "Tên sản phẩm không được trống");

     }
}
