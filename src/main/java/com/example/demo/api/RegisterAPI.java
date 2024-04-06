package com.example.demo.api;


import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/register/validate")
    public Map<String, Object> validateRegister(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (userService.findByUserName(user.getUsername()) != null) {
            response.put("usernameExists", "Tên người dùng đã tồn tại");
            return response; // Trả về kết quả và không thực hiện bất kỳ kiểm tra nào khác
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> fieldErrors = new HashMap<>();
            for (FieldError error : errors) {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            }

            response.put("fieldErrors", fieldErrors);
        } else {
            // Kiểm tra mật khẩu có khớp nhau không
            if (!user.getPassword().equals(user.getRepeatPassword())) {
                response.put("passwordMismatch", "Mật khẩu xác nhận không đúng");
            } else {
                // Trường hợp không có lỗi và mật khẩu khớp nhau
                response.put("successMessage", "Validation successful");
            }
        }
        // Thêm dữ liệu DTO vào phản hồi
        response.put("data", user);
        return response;
    }

    @PostMapping("/login/validate")
    public Map<String, Object> validateLogin(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity UserFromDB = userService.findByUserName(user.getUsername());
        if(UserFromDB == null && !user.getUsername().isEmpty()){
            response.put("usernameErrorLogin", "Tên người dùng chưa đúng ");
            return response;
        }

        if (bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> fieldErrors = new HashMap<>();
            for (FieldError error : errors) {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("fieldErrors", fieldErrors);
        }else if (UserFromDB != null && !passwordEncoder.matches(user.getPassword(), UserFromDB.getPassword())){
            response.put("passwordErrorLogin", "Mật khẩu không đúng");
        }else {
            // Trường hợp không có lỗi và mật khẩu khớp nhau
            response.put("successMessage", "Validation successful");
        }
        response.put("data", user);
        return response;

    }
}
