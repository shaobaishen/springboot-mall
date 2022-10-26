package com.shaobaishen.rowmapper;

import com.shaobaishen.model.Order;
import com.shaobaishen.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItem(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setProductId(rs.getInt("product_id"));
        orderItem.setAmount(rs.getInt("amount"));
        orderItem.setQuantity(rs.getInt("quantity"));

        orderItem.setProduct_name(rs.getString("product_name"));
        orderItem.setImageUrl(rs.getString("image_url"));

        return orderItem;
    }
}
