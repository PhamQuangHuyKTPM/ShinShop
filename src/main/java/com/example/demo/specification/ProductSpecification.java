package com.example.demo.specification;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<ProductEntity> hasCategories(List<CategoryEntity> categories) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
            return categoryJoin.in(categories);
        };
    }
}
