package ro.asis.green.order.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.asis.green.order.service.model.dto.GreenBagDto;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.enums.OrderStatus;
import ro.asis.green.order.service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public OrderDto getByOrderId(@PathVariable String orderId) {
        return orderService.getByOrderId(orderId);
    }

    @GetMapping("/{clientId}")
    public List<OrderDto> getByClientId(@PathVariable String clientId) {
        return orderService.getByClientId(clientId);
    }

    @GetMapping("/{providerId}")
    public List<OrderDto> getByProviderId(@PathVariable String providerId) {
        return orderService.getByProviderId(providerId);
    }

    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable String orderId) {
        return orderService.getOrderStatusByOrderId(orderId);
    }

    @GetMapping("/{orderId}/bags")
    public List<GreenBagDto> getOrderBags(@PathVariable String orderId) {
        return orderService.getBagsByOrderId(orderId);
    }

    @GetMapping("/status")
    public List<OrderDto> getOrdersByStatus(@RequestParam OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto dto) {
        return orderService.addOrder(dto);
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable String orderId, @RequestBody OrderDto dto) {
        return orderService.updateOrder(orderId, dto);
    }

    @DeleteMapping("/{orderId}")
    public OrderDto deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

}
