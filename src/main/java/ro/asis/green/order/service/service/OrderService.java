package ro.asis.green.order.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.asis.green.order.service.exceptions.OrderNotFoundException;
import ro.asis.green.order.service.model.dto.GreenBagDto;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.entity.GreenBag;
import ro.asis.green.order.service.model.entity.OrderEntity;
import ro.asis.green.order.service.model.enums.OrderStatus;
import ro.asis.green.order.service.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private static final String ORDER_ID_NOT_FOUND = "Order with ID %s was not found!";
    private static final String CLIENT_ID_NOT_FOUND = "Order with Client ID %s was not found!";
    private static final String PROVIDER_ID_NOT_FOUND = "Order with Provider ID %s was not found!";
    private static final String STATUS_NOT_FOUND = "Order with status: %s was not found!";

    public List<OrderDto> getAll() {
        List<OrderEntity> orderEntities = repository.findAll();
        return OrderEntity.toOrderDtos(orderEntities);
    }

    public OrderDto getByOrderId(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        return toOrderDto(entity);
    }

    public List<OrderDto> getByClientId(String clientId) {
        List<OrderEntity> entities = getByClientIdOrThrow(clientId);
        return OrderEntity.toOrderDtos(entities);
    }

    public List<OrderDto> getByProviderId(String providerId) {
        List<OrderEntity> entities = getByProviderIdOrThrow(providerId);
        return OrderEntity.toOrderDtos(entities);
    }

    public OrderDto addOrder(OrderDto dto) {
        repository.save(OrderEntity.create(dto));
        return dto;
    }

    public OrderDto updateOrder(String orderId, OrderDto dto) {
        OrderEntity entity = getByIdOrThrow(orderId);
        OrderEntity newEntity = OrderEntity.create(dto);
        newEntity.setId(entity.getId());
        repository.save(newEntity);
        return newEntity.toOrderDto();
    }

    public OrderDto deleteOrder(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        repository.delete(entity);
        return entity.toOrderDto();
    }

    public String getOrderStatusByOrderId(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        return entity.getStatus().name();
    }

    public List<GreenBagDto> getBagsByOrderId(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        List<GreenBag> bags = entity.getBags();
        return GreenBag.toGreenBagDtos(bags);
    }

    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        List<OrderEntity> entities = getByStatusOrThrow(status);
        return OrderEntity.toOrderDtos(entities);
    }


    private OrderEntity getByIdOrThrow(String orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ORDER_ID_NOT_FOUND, orderId)));
    }

    private List<OrderEntity> getByClientIdOrThrow(String clientId) {
        List<OrderEntity> entities = repository.getAllByClientId(clientId);
        if (entities.isEmpty()) {
            throw new OrderNotFoundException(String.format(CLIENT_ID_NOT_FOUND, clientId));
        }
        return entities;
    }

    private List<OrderEntity> getByProviderIdOrThrow(String providerId) {
        List<OrderEntity> entities = repository.getAllByProviderId(providerId);
        if (entities.isEmpty()) {
            throw new OrderNotFoundException(String.format(PROVIDER_ID_NOT_FOUND, providerId));
        }
        return entities;
    }

    private List<OrderEntity> getByStatusOrThrow(OrderStatus status) {
        List<OrderEntity> entities = repository.getAllByStatus(status);
        if (entities.isEmpty()) {
            throw new OrderNotFoundException(String.format(STATUS_NOT_FOUND, status));
        }
        return entities;
    }

    private OrderDto toOrderDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClientId());
        dto.setProviderId(entity.getProviderId());
        dto.setStatus(entity.getStatus());
        dto.setBags(entity.getBags());
        return dto;
    }

}
