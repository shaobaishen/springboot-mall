package com.shaobaishen.service.impl;

import com.shaobaishen.dao.OrderDao;
import com.shaobaishen.dao.ProductDao;
import com.shaobaishen.dao.UserDao;
import com.shaobaishen.dto.BuyItem;
import com.shaobaishen.dto.CreateOrderRequest;
import com.shaobaishen.model.Order;
import com.shaobaishen.model.OrderItem;
import com.shaobaishen.model.Product;
import com.shaobaishen.model.User;
import com.shaobaishen.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // 檢查user是否存在
        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("此userId {} 不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

       int totalAmount =0 ;
       List<OrderItem> orderItemList = new ArrayList<>();

       for(BuyItem buyItem: createOrderRequest.getBuyItemList()) {
           Product product = productDao.getByProductId(buyItem.getProductId());
           // 檢查 product 是否存在、庫存是否足夠
           if (product == null){
               log.warn("商品 {} 不存在" ,buyItem.getProductId());
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
           } else if (product.getStock() < buyItem.getQuantity()){
               log.warn("商品 {} 數量不足，無法購買。剩餘庫存 {} ，欲購買數量 {}" ,buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
           }

           // 扣除商品庫存
           productDao.updateStock(product.getProduct_id(),product.getStock() - buyItem.getQuantity());

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
