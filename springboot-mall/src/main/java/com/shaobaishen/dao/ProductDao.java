package com.shaobaishen.dao;

import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;

public interface ProductDao {

    Product getByProductId(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
