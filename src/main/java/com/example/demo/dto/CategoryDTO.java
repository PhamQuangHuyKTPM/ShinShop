package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CategoryDTO {
    private Integer id;
    @NotBlank(message = "Category name is required")
    @Pattern(regexp = "^[^!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$", message = "Vui lòng không sử dụng kí tự đặc biệt")
    private String categoryName;
    private Boolean categoryStatus;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String categoryName, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
