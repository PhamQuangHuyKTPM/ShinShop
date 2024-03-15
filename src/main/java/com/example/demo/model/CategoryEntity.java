package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;
    private Boolean categoryStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer categoryId, String categoryName, Boolean categoryStatus) {
        this.id = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
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
