package com.aca.cafemanagementsystem.rest;

import com.aca.cafemanagementsystem.dto.OrderDto;
import com.aca.cafemanagementsystem.model.Orders;
import com.aca.cafemanagementsystem.model.Role;
import com.aca.cafemanagementsystem.service.WaiterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/waiter")
public class WaiterController {
    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @PostMapping("/orders")
    @Secured({Role.WAITER, Role.MANAGER} )
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDto orderDto) {
        Orders createdOrder = waiterService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    @PutMapping("/orders/{id}")
    @Secured({Role.WAITER, Role.MANAGER} )
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        Orders updatedOrder = waiterService.updateOrder(orderDto);
        return ResponseEntity.ok(updatedOrder);
    }
    @DeleteMapping("/orders/{id}")
    @Secured({Role.WAITER, Role.MANAGER} )
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        waiterService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
