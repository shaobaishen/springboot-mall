package com.shaobaishen.service.impl;

import com.shaobaishen.dao.ProductDao;
import com.shaobaishen.model.Product;
import com.shaobaishen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getByProductId(Integer productId) {
          return productDao.getByProductId(productId);
    }
}
