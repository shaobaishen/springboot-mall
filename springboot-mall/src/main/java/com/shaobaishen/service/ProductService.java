package com.shaobaishen.service;

import com.shaobaishen.constant.ProductCategory;
import com.shaobaishen.dto.ProductQueryParams;
import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getByProductId(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);

}
