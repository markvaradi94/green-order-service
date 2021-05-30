package ro.asis.green.order.service.model.dto;

import lombok.Data;
import ro.asis.green.order.service.model.entity.GreenBag;
import ro.asis.green.order.service.model.enums.OrderStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {

    @NotBlank
    private String id;

    @NotBlank
    private String clientId;

    @NotBlank
    private String providerId;

    @NotNull
    private OrderStatus status;

    private List<GreenBag> bags;

}
