package com.shaobaishen.dao;

import com.shaobaishen.dto.CreateOrderRequest;
import com.shaobaishen.model.Order;
import com.shaobaishen.model.OrderItem;

import java.util.List;

public interface OrderDao {


    Integer  createOrder(Integer userId , Integer totalAmount );

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemById(Integer orderId);


}
