package com.shaobaishen.service;

import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;

public interface ProductService {

    Product getByProductId(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
