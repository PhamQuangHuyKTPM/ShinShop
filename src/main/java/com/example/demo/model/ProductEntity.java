package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Double price_sale;
    private String description;
    private String color;


    private String image;

    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<ImageEntity> galleries = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<SizeEntity> size = new HashSet<>();

    public ProductEntity() {
    }

    public ProductEntity(Integer id, String name, Double price, Double price_sale, String description, String color, String image, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
        this.description = description;
        this.color = color;
        this.image = image;
        this.category = category;
    }

    public CategoryEntity getCategoryId() {
        return category;
    }

    public void setCategoryId(CategoryEntity category) {
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

    public Set<ImageEntity> getGalleries() {
        return galleries;
    }

    public void setGalleries(Set<ImageEntity> galleries) {
        this.galleries = galleries;
    }

    public Set<SizeEntity> getSize() {
        return size;
    }

    public void setSize(Set<SizeEntity> size) {
        this.size = size;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}

