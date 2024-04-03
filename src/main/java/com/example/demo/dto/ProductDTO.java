package com.example.demo.dto;

import com.example.demo.model.CategoryEntity;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ProductDTO {
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Pattern(regexp = "^[^!@#$%^&*\\[\\]{}\\\\|<>?]*$", message = "Vui lòng không sử dụng kí tự đặc biệt")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "10000", inclusive = false, message = "Giá trị tối thiểu là 10000")
    private Double price;

    @PositiveOrZero(message = "Giá khuyến mãi phải lớn hơn 0 và là số dương")
    private Double price_sale;
    private String description;
    private String color;
    private String image;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    private CategoryEntity category;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, Double price, Double price_sale, String description, String color, String image, LocalDate createdDate, String createdBy, LocalDate modifiedDate, String modifiedBy, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
        this.description = description;
        this.color = color;
        this.image = image;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(Double price_sale) {
        this.price_sale = price_sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
