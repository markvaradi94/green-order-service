package ro.asis.green.order.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.asis.green.order.service.exceptions.OrderNotFoundException;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.entity.OrderEntity;
import ro.asis.green.order.service.model.enums.OrderStatus;
import ro.asis.green.order.service.model.filters.OrderFilters;
import ro.asis.green.order.service.model.mappers.OrderMapper;
import ro.asis.green.order.service.repository.OrderDao;
import ro.asis.green.order.service.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderDao dao;

    private static final String ORDER_ID_NOT_FOUND = "Order with ID %s was not found!";
    private static final String STATUS_NOT_FOUND = "Order with status: %s was not found!";

    public List<OrderDto> getAll(OrderFilters filters) {
        List<OrderEntity> entities = dao.findAll(filters);
        return mapper.toApi(entities);
    }

    public OrderDto getByOrderId(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        return mapper.toApi(entity);
    }

    public OrderDto addOrder(OrderDto dto) {
        return mapper.toApi(repository.save(mapper.toEntity(dto)));
    }

    public OrderDto updateOrder(String orderId, OrderDto dto) {
        OrderEntity entity = getByIdOrThrow(orderId);
        entity.setClientId(dto.getClientId() == null ? entity.getClientId() : dto.getClientId());
        entity.setProviderId(dto.getProviderId() == null ? entity.getProviderId() : dto.getProviderId());
        entity.setStatus(dto.getStatus() == null ? entity.getStatus() : dto.getStatus());
        entity.setBags(dto.getBags() == null || dto.getBags().isEmpty() ? entity.getBags() : dto.getBags());
        repository.save(entity);
        return mapper.toApi(entity);
    }

    public OrderDto deleteOrder(String orderId) {
        OrderEntity entity = getByIdOrThrow(orderId);
        repository.delete(entity);
        return mapper.toApi(entity);
    }

    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        List<OrderEntity> entities = getByStatusOrThrow(status);
        return mapper.toApi(entities);
    }


    private OrderEntity getByIdOrThrow(String orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ORDER_ID_NOT_FOUND, orderId)));
    }

    private List<OrderEntity> getByStatusOrThrow(OrderStatus status) {
        List<OrderEntity> entities = repository.getAllByStatus(status);
        if (entities.isEmpty()) {
            throw new OrderNotFoundException(String.format(STATUS_NOT_FOUND, status));
        }
        return entities;
    }

}
