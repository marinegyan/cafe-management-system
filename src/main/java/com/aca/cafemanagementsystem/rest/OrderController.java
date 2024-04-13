package com.aca.cafemanagementsystem.rest;

import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;
import com.aca.cafemanagementsystem.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{orderId}")
    public  Orders gerOrderById(@PathVariable Long orderId){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderId);
        return orderService.getOrderById(orderDto);
    }

    @GetMapping("/status/{status}")
    public List<Orders> getOrderByStatus(@PathVariable String status){
        OrderDto orderDto = new OrderDto();
        orderDto.setStatus(status);
        return orderService.getOrdersByStatus(orderDto);
    }
    @GetMapping("/table/{tableNumber}")
    public List<Orders> getOrdersByTableNumber(@PathVariable int tableNumber){
        return orderService.getOrdersByTableNumber(tableNumber);
    }
}
