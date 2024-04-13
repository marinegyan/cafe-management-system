package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;

public interface WaiterService {

    Orders createOrder(OrderDto orderDto);

    Orders updateOrder(OrderDto orderDto);

    void deleteOrder(Long orderId);
}
