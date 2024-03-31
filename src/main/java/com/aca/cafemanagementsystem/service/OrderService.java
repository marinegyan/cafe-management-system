package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrderById(Long orderId);
}
