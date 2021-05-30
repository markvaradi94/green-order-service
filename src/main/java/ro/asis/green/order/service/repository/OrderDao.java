package ro.asis.green.order.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.asis.green.order.service.model.entity.OrderEntity;
import ro.asis.green.order.service.model.filters.OrderFilters;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class OrderDao {

    private final MongoTemplate mongoTemplate;

    public List<OrderEntity> findAll(OrderFilters filters) {
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        ofNullable(filters.getClientId())
                .ifPresent(clientId -> criteria.add(Criteria.where("clientId")
                        .is(clientId)));
        ofNullable(filters.getProviderId())
                .ifPresent(providerId -> criteria.add(Criteria.where("providerId")
                        .is(providerId)));
        ofNullable(filters.getStatus())
                .ifPresent(orderStatus -> criteria.add(Criteria.where("status")
                        .is(orderStatus.toUpperCase())));

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query, OrderEntity.class);
    }

}
