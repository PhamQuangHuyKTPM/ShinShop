package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.specification.ProductSpecification;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Integer create(ProductEntity product) {

        ProductEntity savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    @Override
    public ProductEntity findById(Integer id) {
        try{
            Optional<ProductEntity> product = productRepository.findById(id);
            if(product.isPresent()){
                return product.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(ProductEntity product) {
        try{
            productRepository.updateProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            productRepository.delete(findById(id));
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductEntity> filterProducts(List<String> categories, List<String> sizes, Double minPrice, Double maxPrice) {
        return productRepository.filterProducts(categories, sizes, minPrice, maxPrice);
    }

    @Override
    public void saveProductImage(Integer productId, Integer imageId) {
        productRepository.saveProductImage(productId, imageId);
    }

    @Override
    public List<ProductEntity> findAllByOrderByIdDesc() {
        return productRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Object[]> findAllWidthCategoryName() {
        return productRepository.findAllWidthCategoryName();
    }

    @Override
    public List<Object[]> findGalleryByProductId(Integer id) {
        return productRepository.findGalleryByProductId(id);
    }

    @Override
    public void deleteGalleryFromProductId(Integer id) {
        productRepository.deleteGalleryFromProductId(id);
    }

    @Override
    public Integer findGalleryWidthProductId(Integer id) {
        return productRepository.findGalleryWidthProductId(id);
    }

    @Override
    public List<Object[]> findAllSizeOfProduct() {
        return productRepository.findAllSizeOfProduct();
    }

    @Override
    public Set<ProductEntity> findProductByCategory(CategoryEntity category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public List<ProductEntity> filterProduct(Specification<ProductEntity> products) {


        return productRepository.findAll(products);
    }

    @Override
    public List<ProductEntity> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }


    private void mappingDtoWithEntity(){

    }
}
