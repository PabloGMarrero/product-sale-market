package ar.edu.unq.product_sale.infrastructure.web.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

    private String id;

    private String name;

    private Boolean deleted;
}
