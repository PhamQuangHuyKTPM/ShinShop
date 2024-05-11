package com.example.demo.dto;

import java.util.List;

public class FilterDTO {
    private List<Integer> categories;
    private List<String> sizes;
    private Double minPrice;
    private Double maxPrice;

    public FilterDTO() {
    }

    public FilterDTO(List<Integer> categories, List<String> sizes, Double minPrice, Double maxPrice) {
        this.categories = categories;
        this.sizes = sizes;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
