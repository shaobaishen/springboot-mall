package com.shaobaishen.service.impl;

import com.shaobaishen.constant.ProductCategory;
import com.shaobaishen.dao.ProductDao;
import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;
import com.shaobaishen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getByProductId(Integer productId) {
          return productDao.getByProductId(productId);
    }
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
         productDao.updateProduct( productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }
    @Override
    public List<Product> getProducts(ProductCategory category ,String search) {
        return productDao.getProducts(category ,search);
    }
}
