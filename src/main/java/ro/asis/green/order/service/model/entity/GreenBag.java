package ro.asis.green.order.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GreenBag {

    @Id
    private String id;

    private String description;

    private Double price;

    private String imageUrl;

}
