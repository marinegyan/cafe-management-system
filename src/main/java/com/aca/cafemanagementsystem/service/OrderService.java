package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;

import java.util.List;

public interface OrderService {

    Orders getOrderById(OrderDto orderDto);

    List<Orders> getOrdersByStatus(OrderDto orderDto);

    List<Orders> getOrdersByTableNumber(int tableNumber);
}
