package ro.asis.green.order.service.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GreenBagDto {

    @NotBlank
    private String id;

    private String description;

    private Double price;

    private String imageUrl;

}
