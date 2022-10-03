package com.shaobaishen.dao.impl;

import com.shaobaishen.constant.ProductCategory;
import com.shaobaishen.dao.ProductDao;
import com.shaobaishen.dto.ProductQueryParams;
import com.shaobaishen.dto.ProductRequest;
import com.shaobaishen.model.Product;
import com.shaobaishen.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql ="select product_id,product_name, category ,image_url ,price ,stock " +
                ",description ,created_date ,last_modified_date " +
                "from product where 1=1";

        Map<String,Object> map = new HashMap<>();
        if( productQueryParams.getCategory() != null){
            sql = sql + " AND category =:category";
            map.put("category" ,productQueryParams.getCategory().name());
        }
        if( productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search";
            map.put("search" ,"%"+productQueryParams.getSearch()+"%");
        }
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getByProductId(Integer productId) {
        String sql = "select product_id,product_name, category ,image_url " +
                ",price ,stock ,description ,created_date ,last_modified_date " +
                "from product where product_id=:productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert product(product_name,   category  ,image_url  ,price  ,stock  ,description  ,created_date  ,last_modified_date)" +
                           "  values(:product_name, :category ,:image_url ,:price ,:stock ,:description ,:created_date ,:last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProduct_name());
        map.put("category", productRequest.getCategory().toString());  //Enum需成String類型
        map.put("image_url", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        Integer productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql ="update product set product_name=:product_name," +
                " category=:category ,image_url=:image_url ,price=:price ,stock=:stock ," +
                "description=:description ,last_modified_date=:last_modified_date " +
                " where product_id=:productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("product_name",productRequest.getProduct_name());
        map.put("category",productRequest.getCategory().toString());
        map.put("image_url",productRequest.getImage_url());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("last_modified_date",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
    @Override
    public void deleteProduct(Integer productId) {
        String sql = "delete from product where product_id=:productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }


}
