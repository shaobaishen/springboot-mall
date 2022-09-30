package com.shaobaishen.dao;

import com.shaobaishen.model.Product;

public interface ProductDao {

    Product getByProductId(Integer productId);
}
