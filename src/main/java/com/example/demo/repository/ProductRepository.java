package com.example.demo.repository;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product_image (product_id, image_id) VALUES (:productId, :imageId)", nativeQuery = true)
    void saveProductImage(Integer productId, Integer imageId);

    List<ProductEntity> findAllByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query("SELECT p, c.categoryName FROM ProductEntity p INNER JOIN CategoryEntity c ON p.category.id = c.id ORDER BY p.id DESC")
    List<Object[]> findAllWidthCategoryName();

    @Query(value = "SELECT i.id AS image_id, i.image_name FROM images i INNER JOIN product_image pi ON pi.image_id = i.id WHERE pi.product_id = :productId", nativeQuery = true)
    List<Object[]> findGalleryByProductId(Integer productId);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.name = :#{#product.name}, p.price = :#{#product.price}," +
            " p.description = :#{#product.description}, p.image = :#{#product.image}," +
            " p.category  = :#{#product.categoryId}, p.modifiedDate = :#{#product.modifiedDate}," +
            " p.modifiedBy  = :#{#product.modifiedBy} " +
            " WHERE p.id = :#{#product.id}")
    void updateProduct(ProductEntity product);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM product_image WHERE product_id = :id", nativeQuery = true)
    void deleteGalleryFromProductId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "SELECT product_id FROM product_image WHERE product_id = :id LIMIT 1", nativeQuery = true)
    Integer findGalleryWidthProductId(Integer id);

    @Query(value = "SELECT * FROM size", nativeQuery = true)
    List<Object[]> findAllSizeOfProduct();
    @Query(value = "INSERT INTO product_size (product_id, size_id) VALUES (:productId, :sizeId)", nativeQuery = true)
    void saveProductSize(Integer productId, Integer sizeId);

    Set<ProductEntity> findProductByCategory(CategoryEntity category);

}
