package com.shaobaishen.rowmapper;

import com.shaobaishen.constant.ProductCategory;
import com.shaobaishen.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setPrice(rs.getInt("price"));
        product.setProduct_name(rs.getString("product_name"));

        String categoryStr = rs.getString("category");  //datebase取出為String
        ProductCategory productCategory = ProductCategory.valueOf(categoryStr); //categoryStr轉成Enum類型
        product.setCategory(productCategory);
//        product.setCategory(ProductCategory.valueOf(rs.getString("category")));

        product.setImage_url(rs.getString("image_url"));
        product.setStock(rs.getInt("stock"));
        product.setProduct_id(rs.getInt("product_id"));
        product.setDescription(rs.getString("description"));
        product.setCreated_date(rs.getTimestamp("created_date"));
        product.setLast_modified_date(rs.getTimestamp("last_modified_date"));
        return product;
    }
}
