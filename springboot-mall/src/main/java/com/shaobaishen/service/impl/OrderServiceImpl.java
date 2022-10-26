package com.shaobaishen.service.impl;

import com.shaobaishen.dao.OrderDao;
import com.shaobaishen.dao.ProductDao;
import com.shaobaishen.dto.BuyItem;
import com.shaobaishen.dto.CreateOrderRequest;
import com.shaobaishen.model.Order;
import com.shaobaishen.model.OrderItem;
import com.shaobaishen.model.Product;
import com.shaobaishen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
       int totalAmount =0 ;
       List<OrderItem> orderItemList = new ArrayList<>();

       for(BuyItem buyItem: createOrderRequest.getBuyItemList()) {
           Product product = productDao.getByProductId(buyItem.getProductId());
            //  計算總價
           int amount = product.getPrice() * buyItem.getQuantity();
           totalAmount = totalAmount + amount;

           //  轉換 buyItem to orderItem
           OrderItem orderItem = new OrderItem();
           orderItem.setProductId(buyItem.getProductId());
           orderItem.setQuantity(buyItem.getQuantity());
           orderItem.setAmount(amount);

           orderItemList.add(orderItem);
       }


        // 創建訂單
        Integer orderId = orderDao.createOrder(userId ,totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
    @Transactional
    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemById(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
