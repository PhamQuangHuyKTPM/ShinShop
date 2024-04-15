package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<ProductEntity> findAll();

    Integer create(ProductEntity product);
    ProductEntity findById(Integer id);
    void update (ProductEntity product);
    Boolean delete(Integer id);

    void saveProductImage(Integer productId, Integer imageId);

    List<ProductEntity> findAllByOrderByIdDesc();

    List<Object[]> findAllWidthCategoryName();

    List<Object[]> findGalleryByProductId(Integer id);

    void deleteGalleryFromProductId(Integer id);

    Integer findGalleryWidthProductId(Integer id);

    List<Object[]> findAllSizeOfProduct();

    Set<ProductEntity> findProductByCategory(CategoryEntity category);

}
