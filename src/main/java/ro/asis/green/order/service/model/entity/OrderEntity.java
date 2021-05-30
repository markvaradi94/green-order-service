package ro.asis.green.order.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderEntity {

    @Id
    private String id;

    @NotBlank
    private String clientId;

    @NotBlank
    private String providerId;

    @NotNull
    private OrderStatus status;

    @OneToMany
    private List<GreenBag> bags;

    public static OrderEntity create(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setClientId(dto.getClientId());
        entity.setProviderId(dto.getProviderId());
        entity.setStatus(dto.getStatus());
        entity.setBags(dto.getBags());
        return entity;
    }

    public OrderDto toOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setId(id);
        dto.setClientId(clientId);
        dto.setProviderId(providerId);
        dto.setStatus(status);
        dto.setBags(bags);
        return dto;
    }

    public static List<OrderDto> toOrderDtos(List<OrderEntity> entities) {
        return entities.stream()
                .map(OrderEntity::toOrderDto)
                .collect(toList());
    }

}
