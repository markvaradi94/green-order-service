package ro.asis.green.order.service.model.mappers;

import org.springframework.stereotype.Component;
import ro.asis.green.order.service.model.dto.OrderDto;
import ro.asis.green.order.service.model.entity.OrderEntity;

@Component
public class OrderMapper implements Mapper<OrderDto, OrderEntity> {


    @Override
    public OrderDto toApi(OrderEntity source) {
        if (source == null) {
            return null;
        }

        OrderDto target = new OrderDto();
        target.setId(source.getId());
        target.setClientId(source.getClientId());
        target.setProviderId(source.getProviderId());
        target.setStatus(source.getStatus());
        target.setBags(source.getBags());

        return target;
    }

    @Override
    public OrderEntity toEntity(OrderDto source) {
        if (source == null) {
            return null;
        }

        OrderEntity target = new OrderEntity();
        target.setId(source.getId());
        target.setClientId(source.getClientId());
        target.setProviderId(source.getProviderId());
        target.setStatus(source.getStatus());
        target.setBags(source.getBags());

        return target;
    }
}
