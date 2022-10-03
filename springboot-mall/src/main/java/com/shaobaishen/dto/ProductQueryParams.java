package com.shaobaishen.dto;

import com.shaobaishen.constant.ProductCategory;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private String orderBY;
    private String sort;

    public String getOrderBY() {
        return orderBY;
    }

    public void setOrderBY(String orderBY) {
        this.orderBY = orderBY;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
