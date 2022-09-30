package com.shaobaishen.dao;

import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    Product getByProductId(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);

}
