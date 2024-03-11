package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;
    private Boolean categoryStatus;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer categoryId, String categoryName, Boolean categoryStatus) {
        this.id = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer categoryId) {
        this.id = categoryId;
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
