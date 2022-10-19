package com.shaobaishen.service;

import com.shaobaishen.dto.BuyItem;
import com.shaobaishen.dto.CreateOrderRequest;

import java.util.List;

public interface OrderService {

   Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
