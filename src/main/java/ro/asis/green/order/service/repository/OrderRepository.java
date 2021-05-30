package ro.asis.green.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.asis.green.order.service.model.entity.OrderEntity;
import ro.asis.green.order.service.model.enums.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> getAllByClientId(String clientId);

    List<OrderEntity> getAllByProviderId(String providerId);

    List<OrderEntity> getAllByStatus(OrderStatus status);

}
