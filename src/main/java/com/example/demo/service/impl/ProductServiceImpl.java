package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private void mappingDtoWithEntity(){

    }
}
