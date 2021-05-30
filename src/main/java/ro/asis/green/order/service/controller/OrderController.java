package ro.asis.green.order.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.enums.OrderStatus;
import ro.asis.green.order.service.model.filters.OrderFilters;
import ro.asis.green.order.service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll(OrderFilters filters) {
        return orderService.getAll(filters);
    }

    @GetMapping("{orderId}")
    public OrderDto getByOrderId(@PathVariable String orderId) {
        return orderService.getByOrderId(orderId);
    }

    @GetMapping("status")
    public List<OrderDto> getOrdersByStatus(@RequestParam OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto dto) {
        return orderService.addOrder(dto);
    }

    @PatchMapping("{orderId}")
    public OrderDto updateOrder(@PathVariable String orderId, @RequestBody OrderDto dto) {
        return orderService.updateOrder(orderId, dto);
    }

    @DeleteMapping("{orderId}")
    public OrderDto deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

}
