package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.dao.OrderRepository;
import com.aca.cafemanagementsystem.dao.UserRepository;
import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;
import com.aca.cafemanagementsystem.model.OrderStatus;
import com.aca.cafemanagementsystem.service.WaiterService;
import org.springframework.stereotype.Service;

@Service
public class WaiterServiceImpl implements WaiterService {
    private final OrderRepository orderRepository;

    public WaiterServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Orders createOrder(OrderDto orderDto) {
        Orders order = new Orders();
        order.setStatus(orderDto.getStatus());
        order.setTable(orderDto.getTable());
        return orderRepository.save(order);
    }


    @Override
    public Orders updateOrder(OrderDto orderDto) {
        Orders existingOrder = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setStatus(OrderStatus.READY);
        return orderRepository.save(existingOrder);
    }
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
