package ro.asis.green.order.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.asis.green.order.service.model.enums.OrderStatus;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
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

    private List<GreenBag> bags;

}
