package ro.asis.green.order.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.asis.green.order.service.model.dto.GreenBagDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GreenBag {

    @Id
    private String id;

    private String description;

    private Double price;

    private String imageUrl;

    public GreenBagDto toGreenBagDto() {
        GreenBagDto dto = new GreenBagDto();
        dto.setId(id);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setImageUrl(imageUrl);
        return dto;
    }

    public static List<GreenBagDto> toGreenBagDtos(List<GreenBag> bags) {
        return bags.stream()
                .map(GreenBag::toGreenBagDto)
                .collect(toList());
    }

}
