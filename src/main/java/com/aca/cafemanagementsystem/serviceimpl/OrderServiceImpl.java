package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.dao.OrderRepository;
import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;
import com.aca.cafemanagementsystem.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Orders getOrderById(OrderDto orderDto) {
        return  orderRepository.getById(orderDto.getId());
    }

    @Override
    public List<Orders> getOrdersByStatus(OrderDto orderDto) {
        return orderRepository.findByStatus(orderDto.getStatus());
    }

    @Override
    public List<Orders> getOrdersByTableNumber(int tableNumber) {
        return orderRepository.findByTable(tableNumber);
    }
}
